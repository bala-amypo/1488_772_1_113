package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository caseRepo;
    private final StudentProfileRepository studentRepo;

    public IntegrityCaseServiceImpl(
            IntegrityCaseRepository caseRepo,
            StudentProfileRepository studentRepo) {
        this.caseRepo = caseRepo;
        this.studentRepo = studentRepo;
    }

    public IntegrityCase createCase(IntegrityCase integrityCase) {
        return caseRepo.save(integrityCase);
    }

    public IntegrityCase updateCaseStatus(Long caseId, String status) {
        IntegrityCase ic = caseRepo.findById(caseId).orElse(null);
        if (ic == null) return null;
        ic.setStatus(status);
        return caseRepo.save(ic);
    }

    public List<IntegrityCase> getCasesByStudent(Long studentId) {
        StudentProfile s = studentRepo.findById(studentId).orElse(null);
        return caseRepo.findByStudentProfile(s);
    }

    public IntegrityCase getCaseById(Long id) {
        return caseRepo.findById(id).orElse(null);
    }

    public List<IntegrityCase> getAllCases() {
        return caseRepo.findAll();
    }
}
