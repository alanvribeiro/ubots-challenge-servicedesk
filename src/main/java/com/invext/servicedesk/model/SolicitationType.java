package com.invext.servicedesk.model;

public enum SolicitationType {

    CARDS("Cartões"),
    LOANS("Empréstimos"),
    OTHER("Outros Assuntos");

    private final String description;

    SolicitationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
