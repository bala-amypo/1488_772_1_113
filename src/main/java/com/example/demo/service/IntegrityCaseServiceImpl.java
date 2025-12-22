package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IntegrityCaseServiceImpl implements IntegrityCaseService {
    
    private final IntegrityCaseRepository integrityCaseRepository;
    private final StudentProfileRepository studentProfileRepository;
    
    public IntegrityCaseServiceImpl(IntegrityCaseRepository integrityCaseRepository,
                                   StudentProfileRepository studentProfileRepository) {
        this.integrityCaseRepository = integrityCaseRepository;
        this.studentProfileRepository = studentProfileRepository;
    }
    
    @Override
    public IntegrityCase createCase(IntegrityCase integrityCase) {
        // Data validation
        if (integrityCase.getStudentProfile() == null || integrityCase.getStudentProfile().getId() == null) {
            throw new IllegalArgumentException("Student profile is required to create a case");
        }
        
        if (integrityCase.getStatus() == null || integrityCase.getStatus().trim().isEmpty()) {
            integrityCase.setStatus("OPEN");
        }
        
        // Validate student exists
        Long studentId = integrityCase.getStudentProfile().getId();
        StudentProfile studentProfile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found with id: " + studentId));
        
        integrityCase.setStudentProfile(studentProfile);
        
        return integrityCaseRepository.save(integrityCase);
    }
    
    @Override
    public IntegrityCase updateCaseStatus(Long caseId, String newStatus) {
        IntegrityCase integrityCase = integrityCaseRepository.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Integrity case not found with id: " + caseId));
        
        // Validate status
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        
        // Validate status transition
        String currentStatus = integrityCase.getStatus();
        if ("CLOSED".equals(currentStatus) && !"CLOSED".equals(newStatus)) {
            throw new IllegalArgumentException("Cannot change status from CLOSED");
        }
        
        integrityCase.setStatus(newStatus);
        return integrityCaseRepository.save(integrityCase);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<IntegrityCase> getCasesByStudent(Long studentId) {
        return integrityCaseRepository.findByStudentProfile_Id(studentId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<IntegrityCase> getCaseById(Long caseId) {
        return integrityCaseRepository.findById(caseId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<IntegrityCase> getAllCases() {
        return integrityCaseRepository.findAll();
    }
}