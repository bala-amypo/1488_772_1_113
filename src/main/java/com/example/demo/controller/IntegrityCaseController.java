package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) {
        this.service = service;
    }

    // CREATE case
    @PostMapping
    public IntegrityCase createCase(@RequestBody IntegrityCase integrityCase) {
        return service.createCase(integrityCase);
    }

    // UPDATE case status
    @PutMapping("/{id}/status")
    public IntegrityCase updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateCaseStatus(id, status);
    }

    // GET all cases for a student
    @GetMapping("/student/{studentId}")
    public List<IntegrityCase> getCasesByStudent(@PathVariable Long studentId) {
        return service.getCasesByStudent(studentId);
    }
}
