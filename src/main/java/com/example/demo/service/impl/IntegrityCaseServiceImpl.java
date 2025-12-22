package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.IntegrityCaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository caseRepo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository caseRepo) {
        this.caseRepo = caseRepo;
    }

    @Override
    public IntegrityCase createCase(IntegrityCase integrityCase) {
        return caseRepo.save(integrityCase);
    }

    @Override
    public List<IntegrityCase> getAllCases() {
        return caseRepo.findAll();
    }

    @Override
    public IntegrityCase getCaseById(Long id) {
        return caseRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Case not found with id: " + id));
    }
}
