package org.td2.exam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.exam.Service.StatisticsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/collectivites/{id}/statistics")
    public ResponseEntity<Map<String, Object>> getCollectivityStatistics(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Map<String, Object> statistics = statisticsService.getCollectivityStatistics(id, startDate, endDate);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/collectivities/statistics")
    public ResponseEntity<List<Map<String, Object>>> getAllCollectivitiesStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Map<String, Object>> statistics = statisticsService.getAllCollectivitiesStatistics(startDate, endDate);
        return ResponseEntity.ok(statistics);
    }
}