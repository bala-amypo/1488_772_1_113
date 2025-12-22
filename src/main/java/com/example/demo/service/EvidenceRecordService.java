package com.example.demo.service;

import com.example.demo.entity.EvidenceRecord;

import java.util.List;

public interface EvidenceRecordService {
    EvidenceRecord submitEvidence(EvidenceRecord evidence);
    List<EvidenceRecord> getAllEvidence();
}
