package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;

import java.util.List;

public interface RepeatOffenderRecordService {
    RepeatOffenderRecord createRecord(RepeatOffenderRecord record);
    List<RepeatOffenderRecord> getAllRecords();
}
