package com.example.demo.service;

import com.example.demo.entity.RepeatOffenderRecord;
import java.util.List;

public interface RepeatOffenderRecordService {

    RepeatOffenderRecord refreshRepeatOffenderData(Long studentId);

    RepeatOffenderRecord getRecordByStudent(Long studentId);

    List<RepeatOffenderRecord> getAllRepeatOffenders();
}
