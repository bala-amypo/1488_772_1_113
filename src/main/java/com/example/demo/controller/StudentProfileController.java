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
    public ResponseEntity<?> createStudent(@RequestBody StudentProfile studentProfile) {
        try {
            StudentProfile created = studentProfileService.createStudent(studentProfile);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Student created successfully");
            response.put("data", created);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            StudentProfile student = studentProfileService.getStudentById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", student);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<StudentProfile> students = studentProfileService.getAllStudents();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", students);
        response.put("count", students.size());
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}/repeat-status")
    public ResponseEntity<?> updateRepeatStatus(@PathVariable Long id) {
        try {
            StudentProfile updated = studentProfileService.updateRepeatOffenderStatus(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Repeat offender status updated");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}