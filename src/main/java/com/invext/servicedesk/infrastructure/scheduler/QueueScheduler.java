package com.invext.servicedesk.infrastructure.scheduler;

import com.invext.servicedesk.model.SolicitationType;
import com.invext.servicedesk.service.DistributionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class QueueScheduler {

    private static final Logger logger = LoggerFactory.getLogger(QueueScheduler.class);

    private final DistributionService distributionService;

    public QueueScheduler(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    // Agendamento que verifica e redistribui as solicitações enfileiradas a cada 1 minuto.
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkQueueSolicitations() {
        var dateTime = LocalDateTime.now();
        logger.info("### Running schedule at {}", dateTime);
        for (SolicitationType type : SolicitationType.values()) {
            distributionService.distributeSolicitations(type);
        }
    }

}
