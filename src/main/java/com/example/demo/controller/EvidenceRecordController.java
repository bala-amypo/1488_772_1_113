package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceRecordController {
    
    private final EvidenceRecordService evidenceRecordService;
    
    public EvidenceRecordController(EvidenceRecordService evidenceRecordService) {
        this.evidenceRecordService = evidenceRecordService;
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> submitEvidence(@RequestBody EvidenceRecord evidenceRecord) {
        try {
            EvidenceRecord submitted = evidenceRecordService.submitEvidence(evidenceRecord);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Evidence submitted successfully");
            response.put("data", submitted);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<Map<String, Object>> getEvidenceByCase(@PathVariable Long caseId) {
        try {
            // For now, return empty. In real app, you'd have a method to get evidence by case
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Evidence retrieval endpoint");
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