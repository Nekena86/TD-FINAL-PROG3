package org.td2.prog_3.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.prog_3.Services.CollectivityServices;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CollectivityController {

    private CollectivityServices collectivityService;

    public CollectivityController(CollectivityServices collectivityService) {
        this.collectivityService = collectivityService;
    }

    @PostMapping("/collectivities")
    public ResponseEntity<Map<String, Object>> createCollectivity(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = collectivityService.createCollectivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/collectivities/{collectivityId}/identifiers")
    public ResponseEntity<Map<String, Object>> assignIdentifiers(
            @PathVariable Long collectivityId,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = collectivityService.assignIdentifiers(collectivityId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/collectivities/{collectivityId}")
    public ResponseEntity<Map<String, Object>> getCollectivity(@PathVariable Long collectivityId) {
        Map<String, Object> response = collectivityService.getCollectivity(collectivityId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/collectivities")
    public ResponseEntity<List<Map<String, Object>>> getAllCollectivities() {
        List<Map<String, Object>> response = collectivityService.getAllCollectivities();
        return ResponseEntity.ok(response);
    }
}