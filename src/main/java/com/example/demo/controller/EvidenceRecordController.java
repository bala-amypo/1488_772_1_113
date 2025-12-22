package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evidence")
public class EvidenceRecordController {

    private final EvidenceRecordService evidenceService;

    public EvidenceRecordController(EvidenceRecordService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping
    public EvidenceRecord submitEvidence(@RequestBody EvidenceRecord evidence) {
        return evidenceService.submitEvidence(evidence);
    }

    @GetMapping
    public List<EvidenceRecord> getAllEvidence() {
        return evidenceService.getAllEvidence();
    }
}
