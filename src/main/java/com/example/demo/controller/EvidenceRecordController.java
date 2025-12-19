package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.EvidenceRecord;
import com.example.demo.service.EvidenceRecordService;

@RestController
@RequestMapping("/evidence")
public class EvidenceRecordController {

    private final EvidenceRecordService service;

    public EvidenceRecordController(EvidenceRecordService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public EvidenceRecord add(@RequestBody EvidenceRecord e) {
        return service.submitEvidence(e);
    }

    @GetMapping("/show")
    public List<EvidenceRecord> show() {
        return service.getAllEvidence();
    }
}
