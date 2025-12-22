package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
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
            return createSuccessResponse("Evidence submitted successfully", submitted, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEvidenceById(@PathVariable Long id) {
        return evidenceRecordService.getEvidenceById(id)
                .map(evidence -> createSuccessResponse("Evidence retrieved", evidence, HttpStatus.OK))
                .orElseGet(() -> createErrorResponse("Evidence not found with id: " + id, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/case/{caseId}")
    public ResponseEntity<Map<String, Object>> getEvidenceByCase(@PathVariable Long caseId) {
        try {
            List<EvidenceRecord> evidence = evidenceRecordService.getEvidenceByCaseId(caseId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Evidence retrieved");
            response.put("data", evidence);
            response.put("count", evidence.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEvidence() {
        List<EvidenceRecord> evidence = evidenceRecordService.getAllEvidence();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Evidence retrieved");
        response.put("data", evidence);
        response.put("count", evidence.size());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEvidence(@PathVariable Long id) {
        try {
            evidenceRecordService.deleteEvidence(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Evidence deleted successfully");
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