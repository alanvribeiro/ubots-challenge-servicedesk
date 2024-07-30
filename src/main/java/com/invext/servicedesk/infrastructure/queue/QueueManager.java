package com.invext.servicedesk.infrastructure.queue;

import com.invext.servicedesk.model.Attendant;
import com.invext.servicedesk.model.Solicitation;
import com.invext.servicedesk.model.SolicitationType;

import java.util.*;

public class QueueManager {

    // Singleton que gerencia as filas de solicitações e os atendentes disponíveis para cada tipo de solicitação.

    private static QueueManager instance;
    private final Map<SolicitationType, Queue<Solicitation>> queues = new HashMap<>();
    private final Map<SolicitationType, List<Attendant>> attendants = new HashMap<>();

    private QueueManager() {
        for (SolicitationType type : SolicitationType.values()) {
            queues.put(type, new LinkedList<>());
            attendants.put(type, new ArrayList<>());
        }

        // Inicializando atendentes para fins de exemplo
        for (int i = 0; i < 2; i++) {
            attendants.get(SolicitationType.CARDS).add(new Attendant("Card-Attendant" + i));
            attendants.get(SolicitationType.LOANS).add(new Attendant("Loan-Attendant" + i));
            attendants.get(SolicitationType.OTHER).add(new Attendant("Other-Attendant" + i));
        }
    }

    public static QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    // Retorna a fila de solicitações para o tipo especificado
    public Queue<Solicitation> getQueue(SolicitationType type) {
        return queues.get(type);
    }

    // Retorna a lista de atendentes para o tipo especificado
    public List<Attendant> getAttendants(SolicitationType type) {
        return attendants.get(type);
    }

    // Adiciona um atendente a um time específico
    public void addAttendant(SolicitationType type, Attendant attendant) {
        attendants.get(type).add(attendant);
    }

}
