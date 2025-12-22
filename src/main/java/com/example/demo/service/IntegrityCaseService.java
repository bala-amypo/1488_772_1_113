package com.example.demo.service;

import com.example.demo.entity.IntegrityCase;
import java.util.List;

public interface IntegrityCaseService {

    IntegrityCase createCase(IntegrityCase integrityCase);

    IntegrityCase updateCaseStatus(Long caseId, String status);

    List<IntegrityCase> getCasesByStudent(Long studentId);

    IntegrityCase getCaseById(Long id);

    List<IntegrityCase> getAllCases();
}
