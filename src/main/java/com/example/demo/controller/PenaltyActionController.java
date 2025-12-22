package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyActionController {
    
    private final PenaltyActionService penaltyActionService;
    
    public PenaltyActionController(PenaltyActionService penaltyActionService) {
        this.penaltyActionService = penaltyActionService;
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> addPenalty(@RequestBody PenaltyAction penaltyAction) {
        try {
            PenaltyAction added = penaltyActionService.addPenalty(penaltyAction);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Penalty added successfully");
            response.put("data", added);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<Map<String, Object>> getPenaltiesByCase(@PathVariable Long caseId) {
        try {
            // For now, return empty. In real app, you'd have a method to get penalties by case
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Penalties retrieval endpoint");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}