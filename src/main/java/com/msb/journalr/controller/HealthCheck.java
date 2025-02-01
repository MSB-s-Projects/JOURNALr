package com.msb.journalr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("health-check")
public class HealthCheck {
    @GetMapping
    public Map<String, String> checkHealth() {
        Map<String, String> map = new HashMap<>();
        map.put("health", "ok");
        return map;
    }
}
