package com.example.demo.service;

import com.example.demo.entity.EvidenceRecord;
import java.util.List;
import java.util.Optional;

public interface EvidenceRecordService {
    EvidenceRecord submitEvidence(EvidenceRecord evidenceRecord);
    Optional<EvidenceRecord> getEvidenceById(Long id);
    List<EvidenceRecord> getEvidenceByCaseId(Long caseId);
    List<EvidenceRecord> getAllEvidence();
    void deleteEvidence(Long id);
}