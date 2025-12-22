package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    private final EvidenceRecordRepository evidenceRepo;
    private final IntegrityCaseRepository caseRepo;

    public EvidenceRecordServiceImpl(
            EvidenceRecordRepository evidenceRepo,
            IntegrityCaseRepository caseRepo) {
        this.evidenceRepo = evidenceRepo;
        this.caseRepo = caseRepo;
    }

    public EvidenceRecord submitEvidence(EvidenceRecord evidence) {
        return evidenceRepo.save(evidence);
    }

    public List<EvidenceRecord> getEvidenceByCase(Long caseId) {
        IntegrityCase ic = caseRepo.findById(caseId).orElse(null);
        return evidenceRepo.findAll();
    }

    public EvidenceRecord getEvidenceById(Long id) {
        return evidenceRepo.findById(id).orElse(null);
    }

    public List<EvidenceRecord> getAllEvidence() {
        return evidenceRepo.findAll();
    }
}
