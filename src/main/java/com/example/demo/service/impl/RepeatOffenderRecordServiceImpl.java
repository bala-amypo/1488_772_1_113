package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.util.RepeatOffenderCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;
    private final RepeatOffenderRecordRepository repeatRepo;
    private final RepeatOffenderCalculator calculator;

    public RepeatOffenderRecordServiceImpl(
            StudentProfileRepository studentRepo,
            IntegrityCaseRepository caseRepo,
            RepeatOffenderRecordRepository repeatRepo,
            RepeatOffenderCalculator calculator) {
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
        this.repeatRepo = repeatRepo;
        this.calculator = calculator;
    }

    public RepeatOffenderRecord refreshRepeatOffenderData(Long studentId) {
        StudentProfile s = studentRepo.findById(studentId).orElse(null);
        if (s == null) return null;

        List<IntegrityCase> cases = caseRepo.findByStudentProfile(s);
        RepeatOffenderRecord record = calculator.computeRepeatOffenderRecord(s, cases);
        return repeatRepo.save(record);
    }

    public RepeatOffenderRecord getRecordByStudent(Long studentId) {
        StudentProfile s = studentRepo.findById(studentId).orElse(null);
        return repeatRepo.findByStudentProfile(s);
    }

    public List<RepeatOffenderRecord> getAllRepeatOffenders() {
        return repeatRepo.findAll();
    }
}
