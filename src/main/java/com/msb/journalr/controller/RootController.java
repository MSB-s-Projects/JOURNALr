package com.msb.journalr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    @Value("${api.documentation.url}")
    private String apiDocumentation;

    @GetMapping
    public ResponseEntity<?> root() {
        return ResponseEntity.ok(Map.of("message", "Greetings User!", "documentation", apiDocumentation));
    }
}
