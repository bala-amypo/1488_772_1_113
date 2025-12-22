package com.example.demo.service.impl;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EvidenceRecordRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EvidenceRecordServiceImpl implements EvidenceRecordService {
    
    private final EvidenceRecordRepository evidenceRecordRepository;
    private final IntegrityCaseRepository integrityCaseRepository;
    
    public EvidenceRecordServiceImpl(EvidenceRecordRepository evidenceRecordRepository,
                                    IntegrityCaseRepository integrityCaseRepository) {
        this.evidenceRecordRepository = evidenceRecordRepository;
        this.integrityCaseRepository = integrityCaseRepository;
    }
    
    @Override
    public EvidenceRecord submitEvidence(EvidenceRecord evidenceRecord) {
        if (evidenceRecord.getIntegrityCase() == null || evidenceRecord.getIntegrityCase().getId() == null) {
            throw new IllegalArgumentException("Integrity case is required");
        }
        
        Long caseId = evidenceRecord.getIntegrityCase().getId();
        IntegrityCase integrityCase = integrityCaseRepository.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found with id: " + caseId));
        
        evidenceRecord.setIntegrityCase(integrityCase);
        return evidenceRecordRepository.save(evidenceRecord);
    }
}