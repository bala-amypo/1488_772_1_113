package com.example.demo.controller;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repeat-offenders")
public class RepeatOffenderRecordController {

    private final RepeatOffenderRecordService recordService;

    public RepeatOffenderRecordController(RepeatOffenderRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public RepeatOffenderRecord createRecord(@RequestBody RepeatOffenderRecord record) {
        return recordService.createRecord(record);
    }

    @GetMapping
    public List<RepeatOffenderRecord> getAllRecords() {
        return recordService.getAllRecords();
    }
}
