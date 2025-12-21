package com.example.demo.controller;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evidence")
public class EvidenceRecordController {

    private final EvidenceRecordService service;

    public EvidenceRecordController(EvidenceRecordService service) {
        this.service = service;
    }

    // ADD evidence to a case
    @PostMapping
    public EvidenceRecord submitEvidence(@RequestBody EvidenceRecord evidence) {
        return service.submitEvidence(evidence);
    }
}