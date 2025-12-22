package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;

public interface RepeatOffenderRecordService {
    RepeatOffenderRecord calculateAndSaveRepeatOffenderRecord(Long studentId);
}