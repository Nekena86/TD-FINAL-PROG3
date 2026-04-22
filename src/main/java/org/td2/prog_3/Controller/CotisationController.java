package org.td2.prog_3.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.prog_3.Model.Cotisation;
import org.td2.prog_3.Services.CotisationService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/cotisations")
public class CotisationController {

    private CotisationService cotisationService;

    public CotisationController(CotisationService cotisationService) {
        this.cotisationService = cotisationService;
    }

    @PostMapping
    public ResponseEntity<Cotisation> createCotisation(@RequestBody Map<String, Object> request) {
        Double montant = ((Number) request.get("montant")).doubleValue();
        Long membreId = ((Number) request.get("membreId")).longValue();
        Long collectiviteId = ((Number) request.get("collectiviteId")).longValue();
        String type = (String) request.get("type");
        String modePaiement = (String) request.get("modePaiement");

        Cotisation cotisation = cotisationService.createCotisation(montant, membreId, collectiviteId, type, modePaiement);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotisation);
    }
}
