package org.td2.prog_3.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.prog_3.Model.Paiement;
import org.td2.prog_3.Services.PaiementService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/paiements")
public class PaiementController {

    private PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    public ResponseEntity<Paiement> payer(@RequestBody Map<String, Object> request) {
        Double montant = ((Number) request.get("montant")).doubleValue();
        Long membreId = ((Number) request.get("membreId")).longValue();
        Long cotisationId = ((Number) request.get("cotisationId")).longValue();
        String mode = (String) request.get("mode");

        Paiement paiement = paiementService.payer(montant, membreId, cotisationId, mode);
        return ResponseEntity.status(HttpStatus.CREATED).body(paiement);
    }
}
