package org.example.controllers;

import org.example.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping({"/statistics", "/api/statistics"})
public class StatisticsController {
    
    @Autowired
    private StatisticsService statisticsService;
    
    @GetMapping("/global")
    public Map<String, Integer> getGlobalStatistics() {
        return statisticsService.getGlobalStatistics();
    }
}