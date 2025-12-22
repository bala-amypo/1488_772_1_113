package com.example.demo.controller;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/repeat-offenders")
public class RepeatOffenderRecordController {
    
    private final RepeatOffenderRecordService repeatOffenderRecordService;
    
    public RepeatOffenderRecordController(RepeatOffenderRecordService repeatOffenderRecordService) {
        this.repeatOffenderRecordService = repeatOffenderRecordService;
    }
    
    @PostMapping("/calculate/{studentId}")
    public ResponseEntity<Map<String, Object>> calculateRepeatOffender(@PathVariable Long studentId) {
        try {
            RepeatOffenderRecord record = repeatOffenderRecordService.calculateAndSaveRepeatOffenderRecord(studentId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Repeat offender record calculated");
            response.put("data", record);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getRepeatOffenderRecord(@PathVariable Long studentId) {
        try {
            // For now, calculate and return. In real app, you'd have a get method
            RepeatOffenderRecord record = repeatOffenderRecordService.calculateAndSaveRepeatOffenderRecord(studentId);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", record);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}