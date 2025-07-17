package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "API health API")
public class HealthController {

    @GetMapping
    @Operation(summary = "Check API health", description = "Returns ok if API is available")
    public ResponseEntity<String> checkHealth(){
        return ResponseEntity.ok("ok");
    }
}
