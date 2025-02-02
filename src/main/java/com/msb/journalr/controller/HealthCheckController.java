package com.msb.journalr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("health-check")
public class HealthCheckController {
    @GetMapping
    public ResponseEntity<?> checkHealth() {
        return ResponseEntity.ok(Map.of("health", "ok"));
    }
}
