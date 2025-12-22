package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) {
        this.service = service;
    }

    @PostMapping
    public IntegrityCase create(@RequestBody IntegrityCase ic) {
        return service.createCase(ic);
    }

    @PutMapping("/{id}/status")
    public IntegrityCase updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateCaseStatus(id, status);
    }

    @GetMapping("/student/{studentId}")
    public List<IntegrityCase> getByStudent(@PathVariable Long studentId) {
        return service.getCasesByStudent(studentId);
    }

    @GetMapping("/{id}")
    public IntegrityCase getById(@PathVariable Long id) {
        return service.getCaseById(id);
    }

    @GetMapping
    public List<IntegrityCase> getAll() {
        return service.getAllCases();
    }
}
