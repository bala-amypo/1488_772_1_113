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
        // Validation
        if (integrityCase.getStudentProfile() == null || integrityCase.getStudentProfile().getId() == null) {
            throw new IllegalArgumentException("Student profile is required");
        }
        
        Long studentId = integrityCase.getStudentProfile().getId();
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        
        integrityCase.setStudentProfile(student);
        return integrityCaseRepository.save(integrityCase);
    }
    
    @Override
    public IntegrityCase updateCaseStatus(Long caseId, String newStatus) {
        IntegrityCase integrityCase = integrityCaseRepository.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found with id: " + caseId));
        
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
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