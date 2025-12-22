package com.example.demo.service.impl;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.repository.PenaltyActionRepository;
import com.example.demo.service.PenaltyActionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository penaltyRepo;

    public PenaltyActionServiceImpl(PenaltyActionRepository penaltyRepo) {
        this.penaltyRepo = penaltyRepo;
    }

    @Override
    public PenaltyAction addPenalty(PenaltyAction penalty) {
        return penaltyRepo.save(penalty);
    }

    @Override
    public List<PenaltyAction> getAllPenalties() {
        return penaltyRepo.findAll();
    }
}
