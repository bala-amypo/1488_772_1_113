package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService integrityCaseService;

    public IntegrityCaseController(IntegrityCaseService integrityCaseService) {
        this.integrityCaseService = integrityCaseService;
    }

    @PostMapping
    public ResponseEntity<IntegrityCase> create(
            @RequestBody IntegrityCase integrityCase) {

        return ResponseEntity.ok(
                integrityCaseService.createCase(integrityCase)
        );
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<IntegrityCase> updateStatus(
            @PathVariable Long id,
            @PathVariable String status) {

        return ResponseEntity.ok(
                integrityCaseService.updateCaseStatus(id, status)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<IntegrityCase>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                integrityCaseService.getCasesByStudent(studentId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntegrityCase> getById(
            @PathVariable Long id) {

        Optional<IntegrityCase> result =
                integrityCaseService.getCaseById(id);

        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}