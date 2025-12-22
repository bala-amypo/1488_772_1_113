package com.example.demo.service.impl;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {

    private final RepeatOffenderRecordRepository recordRepo;

    public RepeatOffenderRecordServiceImpl(RepeatOffenderRecordRepository recordRepo) {
        this.recordRepo = recordRepo;
    }

    @Override
    public RepeatOffenderRecord createRecord(RepeatOffenderRecord record) {
        return recordRepo.save(record);
    }

    @Override
    public List<RepeatOffenderRecord> getAllRecords() {
        return recordRepo.findAll();
    }
}
