package com.invext.servicedesk.service;

import com.invext.servicedesk.model.Solicitation;
import com.invext.servicedesk.model.SolicitationType;

public interface DistributionService {

    void addSolicitation(Solicitation solicitation);
    void distributeSolicitations(SolicitationType type);

}
