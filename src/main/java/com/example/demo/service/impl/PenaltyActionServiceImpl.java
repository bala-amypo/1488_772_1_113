package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PenaltyAction addPenalty(PenaltyAction penalty) {
        return penaltyRepo.save(penalty);
    }

    public List<PenaltyAction> getPenaltiesByCase(Long caseId) {
        return penaltyRepo.findAll();
    }

    public PenaltyAction getPenaltyById(Long id) {
        return penaltyRepo.findById(id).orElse(null);
    }

    public List<PenaltyAction> getAllPenalties() {
        return penaltyRepo.findAll();
    }
}
