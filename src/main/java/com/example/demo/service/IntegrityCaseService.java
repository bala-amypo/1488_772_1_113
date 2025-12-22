package com.example.demo.service;

import com.example.demo.entity.IntegrityCase;

import java.util.List;

public interface IntegrityCaseService {
    IntegrityCase createCase(IntegrityCase integrityCase);
    List<IntegrityCase> getAllCases();
    IntegrityCase getCaseById(Long id);
}
