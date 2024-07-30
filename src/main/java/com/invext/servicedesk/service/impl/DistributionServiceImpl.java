package com.invext.servicedesk.service.impl;

import com.invext.servicedesk.infrastructure.queue.QueueManager;
import com.invext.servicedesk.model.Attendant;
import com.invext.servicedesk.model.Solicitation;
import com.invext.servicedesk.model.SolicitationType;
import com.invext.servicedesk.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Service
public class DistributionServiceImpl implements DistributionService {

    private final QueueManager queueManager;

    @Autowired
    public DistributionServiceImpl() {
        this.queueManager = QueueManager.getInstance();
    }

    // Adiciona uma nova solicitação à fila e tenta distribuí-la imediatamente
    @Override
    public void addSolicitation(Solicitation solicitation) {
        queueManager.getQueue(solicitation.getType()).offer(solicitation);
        distributeSolicitations(solicitation.getType());
    }

    // Distribui as solicitações da fila para os atendentes disponíveis
    public void distributeSolicitations(SolicitationType type) {
        Queue<Solicitation> queue = queueManager.getQueue(type);
        List<Attendant> attendants = queueManager.getAttendants(type);
        distribute(attendants, queue);
    }

    private void distribute(List<Attendant> attendants, Queue<Solicitation> queue) {
        for (Attendant attendant : attendants) {
            while (!queue.isEmpty() && attendant.canHandleMoreRequests()) {
                Solicitation solicitation = queue.poll();
                if (solicitation != null) {
                    attendant.addSolicitation(solicitation);
                }
            }
        }
    }

}
