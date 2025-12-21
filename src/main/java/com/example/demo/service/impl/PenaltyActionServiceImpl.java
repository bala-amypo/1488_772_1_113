package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyActionService;
import org.springframework.stereotype.Service;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository penaltyRepo;
    private final IntegrityCaseRepository caseRepo;

    public PenaltyActionServiceImpl(
            PenaltyActionRepository penaltyRepo,
            IntegrityCaseRepository caseRepo) {
        this.penaltyRepo = penaltyRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public PenaltyAction addPenalty(PenaltyAction penalty) {
        Long caseId = penalty.getIntegrityCase().getId();

        IntegrityCase c = caseRepo.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found"));

        c.setStatus("UNDER_REVIEW");
        caseRepo.save(c);

        return penaltyRepo.save(penalty);
    }
}
