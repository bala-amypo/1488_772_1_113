package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
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
            return createSuccessResponse("Penalty added successfully", added, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPenaltyById(@PathVariable Long id) {
        return penaltyActionService.getPenaltyById(id)
                .map(penalty -> createSuccessResponse("Penalty retrieved", penalty, HttpStatus.OK))
                .orElseGet(() -> createErrorResponse("Penalty not found with id: " + id, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<Map<String, Object>> getPenaltiesByCase(@PathVariable Long caseId) {
        try {
            List<PenaltyAction> penalties = penaltyActionService.getPenaltiesByCaseId(caseId);
            Map<String, Object> response = createSuccessResponse("Penalties retrieved", penalties, HttpStatus.OK);
            response.put("count", penalties.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPenalties() {
        List<PenaltyAction> penalties = penaltyActionService.getAllPenalties();
        Map<String, Object> response = createSuccessResponse("Penalties retrieved", penalties, HttpStatus.OK);
        response.put("count", penalties.size());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePenalty(@PathVariable Long id) {
        try {
            penaltyActionService.deletePenalty(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Penalty deleted successfully");
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