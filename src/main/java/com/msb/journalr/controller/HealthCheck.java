package com.msb.journalr.controller;

import com.msb.journalr.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("health-check")
public class HealthCheck {
    @GetMapping
    public Response checkHealth() {
        return new Response(Map.of("health", "ok"));
    }
}
