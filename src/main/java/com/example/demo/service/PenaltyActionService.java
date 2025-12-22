package com.example.demo.service;

import com.example.demo.entity.PenaltyAction;
import java.util.List;
import java.util.Optional;

public interface PenaltyActionService {
    PenaltyAction addPenalty(PenaltyAction penaltyAction);
    Optional<PenaltyAction> getPenaltyById(Long id);
    List<PenaltyAction> getPenaltiesByCaseId(Long caseId);
    List<PenaltyAction> getAllPenalties();
    void deletePenalty(Long id);
}