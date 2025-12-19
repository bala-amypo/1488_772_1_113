package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.EvidenceRecord;
import com.example.demo.repository.EvidenceRecordRepository;

@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    private final EvidenceRecordRepository repo;

    public EvidenceRecordServiceImpl(EvidenceRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public EvidenceRecord submitEvidence(EvidenceRecord evidence) {

        // ✅ Rule 1: Evidence cannot be empty
        if (evidence.getContent() == null || evidence.getContent().trim().isEmpty()) {
            throw new RuntimeException("Evidence content cannot be empty");
        }

        evidence.setSubmittedAt(LocalDateTime.now());
        return repo.save(evidence);
    }

    @Override
    public List<EvidenceRecord> getAllEvidence() {
        return repo.findAll();
    }
}
