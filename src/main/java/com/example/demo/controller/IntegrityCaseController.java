package com.example.demo.controller;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService caseService;

    public IntegrityCaseController(IntegrityCaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    public IntegrityCase createCase(@RequestBody IntegrityCase integrityCase) {
        return caseService.createCase(integrityCase);
    }

    @GetMapping
    public List<IntegrityCase> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/{id}")
    public IntegrityCase getCaseById(@PathVariable Long id) {
        return caseService.getCaseById(id);
    }
}
