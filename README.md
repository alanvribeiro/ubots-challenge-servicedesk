# Desafio de Código da Ubots - Invext Solicitation Distribution System

## Descrição do Desafio

A Invext é uma fintech que está estruturando sua central de relacionamento. Esta central de relacionamento irá atender diversos tipos de solicitações dos clientes, tais como:
- Problemas com cartão
- Contratação de empréstimo
- Outros assuntos

A Invext organizou seus atendentes em 3 times de atendimento: Cartões, Empréstimos e Outros Assuntos. A aplicação deve distribuir as solicitações para o time correto de acordo com o seu tipo. Cada atendente pode atender no máximo 3 solicitações simultaneamente. Se todos os atendentes de um time estiverem ocupados, as solicitações devem ser enfileiradas e distribuídas assim que possível.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Scheduling

## Endpoints da API

### Criar uma Solicitação

- **URL**: `/api/solicitations`
- **Método**: `POST`
- **Corpo da Requisição**:
    ```json
    {
        "type": "CARDS",
        "description": "Problemas com cartão"
    }
    ```
    ```json
    {
        "type": "LOANS",
        "description": "Contratação de empréstimo"
    }
    ```
    ```json
    {
        "type": "OTHER",
        "description": "Outros Assuntos"
    }
    ```
- **Respostas**:
    - `201 Created` se a solicitação for criada com sucesso.
    - `400 Bad Request` se o tipo de solicitação for inválido.

## Como Rodar a Aplicação

1. Clone o repositório:
    ```bash
    git clone https://github.com/alanvribeiro/ubots-challenge-servicedesk.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd servicedesk
    ```
3. Compile e rode a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```
4. Acesse a API através do endpoint exposto.
    ```bash
    http://localhost:8080/swagger-ui/index.html
    ```

## Como o Problema foi Resolvido

### QueueManager

Gerencia as filas de solicitações e a lista de atendentes disponíveis para cada tipo de solicitação. Foi utilizado o padrão Singleton para garantir que apenas uma instância da classe gerencie as filas e os atendentes.

### DistributionService

Gerencia a adição de novas solicitações e a sua distribuição conforme a disponibilidade dos atendentes. Foi utilizado o padrão Strategy para distribuir as solicitações e streams para uma abordagem funcional.

### QueueScheduler

Serviço agendado que periodicamente verifica e redistribui as solicitações enfileiradas. A anotação `@Scheduled` com `fixedDelay` garante que a redistribuição de solicitações ocorra a cada 1 minuto, atendendo ao requisito de que solicitações sejam distribuídas assim que possível.

### SolicitationController

Controlador que expõe a API REST para criar novas solicitações. Recebe solicitações via POST, valida o tipo de solicitação e adiciona à fila correspondente.

### Attendant

Classe que representa um atendente, com capacidade de atender até 3 solicitações simultaneamente. Mantém uma lista das solicitações atuais e verifica se pode atender mais solicitações.

### Solicitation

Classe que representa uma solicitação com seu tipo e descrição.

### SolicitationType

E um enum para representar os diferentes tipos de solicitações (`CARDS`, `LOANS`, `OTHER`).

## Conclusão

Com essa abordagem, a aplicação é organizada em diferentes pacotes que separam as responsabilidades, facilitando a manutenção e a escalabilidade. A implementação segue princípios de design sólidos, como o uso de enums, padrões de projeto como Singleton e Strategy, e uma abordagem modular para a distribuição de solicitações.
