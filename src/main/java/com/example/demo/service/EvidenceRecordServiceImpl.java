package com.example.demo.service.impl;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    private final EvidenceRecordRepository evidenceRepo;

    public EvidenceRecordServiceImpl(EvidenceRecordRepository evidenceRepo) {
        this.evidenceRepo = evidenceRepo;
    }

    @Override
    public EvidenceRecord submitEvidence(EvidenceRecord evidence) {
        return evidenceRepo.save(evidence);
    }

    @Override
    public List<EvidenceRecord> getAllEvidence() {
        return evidenceRepo.findAll();
    }
}
