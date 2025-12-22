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
            return createSuccessResponse("Case created successfully", created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCaseById(@PathVariable Long id) {
        return integrityCaseService.getCaseById(id)
                .map(caseObj -> createSuccessResponse("Case retrieved", caseObj, HttpStatus.OK))
                .orElseGet(() -> createErrorResponse("Case not found with id: " + id, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCases() {
        List<IntegrityCase> cases = integrityCaseService.getAllCases();
        Map<String, Object> response = createSuccessResponse("Cases retrieved", cases, HttpStatus.OK);
        response.put("count", cases.size());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getCasesByStudent(@PathVariable Long studentId) {
        try {
            List<IntegrityCase> cases = integrityCaseService.getCasesByStudent(studentId);
            Map<String, Object> response = createSuccessResponse("Cases retrieved", cases, HttpStatus.OK);
            response.put("count", cases.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateCaseStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String newStatus = request.get("status");
            if (newStatus == null || newStatus.trim().isEmpty()) throw new IllegalArgumentException("Status is required");
            IntegrityCase updated = integrityCaseService.updateCaseStatus(id, newStatus);
            return createSuccessResponse("Case status updated", updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCase(@PathVariable Long id, @RequestBody IntegrityCase integrityCase) {
        try {
            IntegrityCase updated = integrityCaseService.updateCase(id, integrityCase);
            return createSuccessResponse("Case updated", updated, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCase(@PathVariable Long id) {
        try {
            integrityCaseService.deleteCase(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Case deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    // Helper methods
    private ResponseEntity<Map<String, Object>> createSuccessResponse(String message, Object data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.status(status).body(response);
    }
    
    private ResponseEntity<Map<String, Object>> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
}