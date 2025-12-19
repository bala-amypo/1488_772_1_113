package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.util.RepeatOffenderCalculator;

@Service
public class RepeatOffenderRecordServiceImpl
        implements RepeatOffenderRecordService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;
    private final RepeatOffenderRecordRepository recordRepo;
    private final RepeatOffenderCalculator calculator;

    // ✅ Constructor order EXACT as required
    public RepeatOffenderRecordServiceImpl(
            StudentProfileRepository studentRepo,
            IntegrityCaseRepository caseRepo,
            RepeatOffenderRecordRepository recordRepo,
            RepeatOffenderCalculator calculator) {

        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
        this.recordRepo = recordRepo;
        this.calculator = calculator;
    }

    @Override
    public RepeatOffenderRecord refreshRepeatOffenderData(Long studentId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        List<IntegrityCase> cases =
                caseRepo.findByStudentProfile(student);

        // Rule: generate only if >= 2 cases
        if (cases.size() < 2) {
            return null;
        }

        RepeatOffenderRecord record =
                calculator.computeRepeatOffenderRecord(student, cases);

        student.setIsRepeatOffender(true);
        studentRepo.save(student);

        return recordRepo.save(record);
    }

    @Override
    public List<RepeatOffenderRecord> getAllRepeatOffenders() {
        return recordRepo.findAll();
    }
}
