package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository repo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository repo) {
        this.repo = repo;
    }

    public IntegrityCase createCase(IntegrityCase c) {
        if (c.getStudentProfile() == null)
            throw new IllegalArgumentException("Student profile required");
        return repo.save(c);
    }

    public IntegrityCase updateCaseStatus(Long id, String status) {
        IntegrityCase c = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Case not found"));
        c.setStatus(status);
        return repo.save(c);
    }

    public List<IntegrityCase> getCasesByStudent(Long studentId) {
        return repo.findByStudentProfile_Id(studentId);
    }
}
