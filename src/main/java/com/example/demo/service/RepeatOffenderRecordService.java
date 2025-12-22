package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;
import java.util.List;
import java.util.Optional;

public interface RepeatOffenderRecordService {
    RepeatOffenderRecord calculateAndSaveRepeatOffenderRecord(Long studentId);
    Optional<RepeatOffenderRecord> getRecordByStudentId(Long studentId);
    List<RepeatOffenderRecord> getAllRecords();
    void deleteRecord(Long id);
}