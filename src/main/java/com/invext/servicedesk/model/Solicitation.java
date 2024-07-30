package com.invext.servicedesk.model;

public class Solicitation {

    private SolicitationType type;
    private String description;

    public Solicitation(SolicitationType type, String description) {
        this.type = type;
        this.description = description;
    }

    public SolicitationType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

}
