package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.PenaltyAction;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.repository.PenaltyActionRepository;

@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository penaltyRepo;
    private final IntegrityCaseRepository caseRepo;

    // ✅ Constructor Injection (as required)
    public PenaltyActionServiceImpl(PenaltyActionRepository penaltyRepo,
                                    IntegrityCaseRepository caseRepo) {
        this.penaltyRepo = penaltyRepo;
        this.caseRepo = caseRepo;
    }

    @Override
    public PenaltyAction addPenalty(PenaltyAction p) {

        // Rule 1: Penalty must be tied to a case
        IntegrityCase ic = caseRepo.findById(
                p.getIntegrityCase().getId()
        ).orElseThrow(() ->
            new RuntimeException("Integrity Case not found")
        );

        // Auto status update rule
        String penalty = p.getPenaltyType();

        if (penalty.equalsIgnoreCase("WARNING")
                || penalty.equalsIgnoreCase("GRADE_REDUCTION")) {
            ic.setStatus("UNDER_REVIEW");
        } else if (penalty.equalsIgnoreCase("ZERO_MARK")
                || penalty.equalsIgnoreCase("SUSPENSION")) {
            ic.setStatus("RESOLVED");
        }

        caseRepo.save(ic);

        p.setIssuedAt(LocalDateTime.now());
        p.setIntegrityCase(ic);

        return penaltyRepo.save(p);
    }

    @Override
    public List<PenaltyAction> getAllPenalties() {
        return penaltyRepo.findAll();
    }
}
