package com.invext.servicedesk.controller;

import com.invext.servicedesk.model.Solicitation;
import com.invext.servicedesk.model.SolicitationType;
import com.invext.servicedesk.service.DistributionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/solicitations")
public class SolicitationController {

    private final DistributionService distributionService;

    public SolicitationController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    // Endpoint para criar uma nova solicitação
    @PostMapping
    public ResponseEntity<Void> createSolicitation(@RequestBody Map<String, String> request) {
        String typeString = request.get("type");
        String description = request.get("description");

        SolicitationType type;
        try {
            type = SolicitationType.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Solicitation solicitation = new Solicitation(type, description);
        distributionService.addSolicitation(solicitation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
