package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cases")
public class IntegrityCaseController {
    
    private final IntegrityCaseService integrityCaseService;
    
    public IntegrityCaseController(IntegrityCaseService integrityCaseService) {
        this.integrityCaseService = integrityCaseService;
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCase(@RequestBody IntegrityCase integrityCase) {
        try {
            IntegrityCase created = integrityCaseService.createCase(integrityCase);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Case created successfully");
            response.put("data", created);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateCaseStatus(@PathVariable Long id, 
                                                                @RequestBody Map<String, String> request) {
        try {
            String newStatus = request.get("status");
            if (newStatus == null || newStatus.trim().isEmpty()) {
                throw new IllegalArgumentException("Status is required");
            }
            
            IntegrityCase updated = integrityCaseService.updateCaseStatus(id, newStatus);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Case status updated");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getCasesByStudent(@PathVariable Long studentId) {
        try {
            List<IntegrityCase> cases = integrityCaseService.getCasesByStudent(studentId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", cases);
            response.put("count", cases.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCaseById(@PathVariable Long id) {
        return integrityCaseService.getCaseById(id)
                .map(caseObj -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", true);
                    response.put("data", caseObj);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", false);
                    response.put("message", "Case not found with id: " + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCases() {
        List<IntegrityCase> cases = integrityCaseService.getAllCases();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", cases);
        response.put("count", cases.size());
        return ResponseEntity.ok(response);
    }
}