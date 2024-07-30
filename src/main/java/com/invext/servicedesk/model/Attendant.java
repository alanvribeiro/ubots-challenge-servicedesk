package com.invext.servicedesk.model;

import java.util.ArrayList;
import java.util.List;

public class Attendant {

    private String name;
    private List<Solicitation> currentSolicitations = new ArrayList<>();
    private static final int MAX_CAPACITY = 3;

    public Attendant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Solicitation> getCurrentSolicitations() {
        return currentSolicitations;
    }

    // Verifica se o atendente pode atender mais solicitações
    public boolean canHandleMoreRequests() {
        return currentSolicitations.size() < MAX_CAPACITY;
    }

    // Adiciona uma solicitação ao atendente
    public void addSolicitation(Solicitation solicitation) {
        currentSolicitations.add(solicitation);
    }

}
