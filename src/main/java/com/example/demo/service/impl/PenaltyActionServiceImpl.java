package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.PenaltyActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PenaltyActionServiceImpl implements PenaltyActionService {
    private final PenaltyActionRepository penaltyActionRepository;
    private final IntegrityCaseRepository integrityCaseRepository;
    
    public PenaltyActionServiceImpl(PenaltyActionRepository penaltyActionRepository, IntegrityCaseRepository integrityCaseRepository) {
        this.penaltyActionRepository = penaltyActionRepository;
        this.integrityCaseRepository = integrityCaseRepository;
    }
    
    @Override
    public PenaltyAction addPenalty(PenaltyAction penaltyAction) {
        if (penaltyAction.getIntegrityCase() == null || penaltyAction.getIntegrityCase().getId() == null) {
            throw new IllegalArgumentException("Integrity case is required");
        }
        Long caseId = penaltyAction.getIntegrityCase().getId();
        IntegrityCase integrityCase = integrityCaseRepository.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found with id: " + caseId));
        penaltyAction.setIntegrityCase(integrityCase);
        if ("OPEN".equals(integrityCase.getStatus())) {
            integrityCase.setStatus("UNDER_REVIEW");
            integrityCaseRepository.save(integrityCase);
        }
        return penaltyActionRepository.save(penaltyAction);
    }
    
    @Override @Transactional(readOnly = true)
    public Optional<PenaltyAction> getPenaltyById(Long id) {
        return penaltyActionRepository.findById(id);
    }
    
    @Override @Transactional(readOnly = true)
    public List<PenaltyAction> getPenaltiesByCaseId(Long caseId) {
        return penaltyActionRepository.findByIntegrityCase_Id(caseId);
    }
    
    @Override @Transactional(readOnly = true)
    public List<PenaltyAction> getAllPenalties() {
        return penaltyActionRepository.findAll();
    }
    
    @Override
    public void deletePenalty(Long id) {
        PenaltyAction penaltyAction = penaltyActionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Penalty not found with id: " + id));
        penaltyActionRepository.delete(penaltyAction);
    }
}