package com.eater.eater.admin.controller;

import com.eater.eater.admin.dto.statistic.StatisticDTO;
import com.eater.eater.admin.service.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stats")
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticDTO> getStatistics() {
        StatisticDTO response = statsService.getStatistics();
        return ResponseEntity.ok(response);
    }
}
