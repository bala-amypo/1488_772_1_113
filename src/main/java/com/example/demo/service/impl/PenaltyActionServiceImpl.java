package com.example.demo.service.impl;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.PenaltyAction;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.service.PenaltyActionService;
import org.springframework.stereotype.Service;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository penaltyActionRepository;
    private final IntegrityCaseRepository integrityCaseRepository;

    public PenaltyActionServiceImpl(
            PenaltyActionRepository penaltyActionRepository,
            IntegrityCaseRepository integrityCaseRepository) {

        this.penaltyActionRepository = penaltyActionRepository;
        this.integrityCaseRepository = integrityCaseRepository;
    }

    @Override
    public PenaltyAction addPenalty(PenaltyAction penaltyAction) {

        IntegrityCase integrityCase = integrityCaseRepository
                .findById(penaltyAction.getIntegrityCase().getId())
                .orElseThrow(() -> new IllegalArgumentException("Case not found"));

        integrityCase.setStatus("UNDER_REVIEW");
        integrityCaseRepository.save(integrityCase);

        penaltyAction.setIntegrityCase(integrityCase);
        return penaltyActionRepository.save(penaltyAction);
    }
}
