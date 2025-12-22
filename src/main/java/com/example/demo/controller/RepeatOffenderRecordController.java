package com.example.demo.controller;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
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
            return createSuccessResponse("Repeat offender record calculated", record, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getRepeatOffenderRecord(@PathVariable Long studentId) {
        return repeatOffenderRecordService.getRecordByStudentId(studentId)
                .map(record -> createSuccessResponse("Repeat offender record retrieved", record, HttpStatus.OK))
                .orElseGet(() -> createErrorResponse("No repeat offender record found for student id: " + studentId, HttpStatus.NOT_FOUND));
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRecords() {
        List<RepeatOffenderRecord> records = repeatOffenderRecordService.getAllRecords();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Repeat offender records retrieved");
        response.put("data", records);
        response.put("count", records.size());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRecord(@PathVariable Long id) {
        try {
            repeatOffenderRecordService.deleteRecord(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Record deleted successfully");
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