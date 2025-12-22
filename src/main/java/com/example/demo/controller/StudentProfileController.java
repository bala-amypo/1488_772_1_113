package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    private final StudentProfileService studentProfileService;
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody StudentProfile studentProfile) {
        try {
            StudentProfile created = studentProfileService.createStudent(studentProfile);
            return createSuccessResponse("Student created successfully", created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable Long id) {
        try {
            StudentProfile student = studentProfileService.getStudentById(id);
            return createSuccessResponse("Student retrieved", student, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        List<StudentProfile> students = studentProfileService.getAllStudents();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Students retrieved");
        response.put("data", students);
        response.put("count", students.size());
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        try {
            StudentProfile updated = studentProfileService.updateStudent(id, studentProfile);
            return createSuccessResponse("Student updated successfully", updated, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Long id) {
        try {
            studentProfileService.deleteStudent(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Student deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/repeat-status")
    public ResponseEntity<Map<String, Object>> updateRepeatStatus(@PathVariable Long id) {
        try {
            StudentProfile updated = studentProfileService.updateRepeatOffenderStatus(id);
            return createSuccessResponse("Repeat offender status updated", updated, HttpStatus.OK);
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