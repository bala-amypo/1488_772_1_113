package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.util.RepeatOffenderCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;
    private final IntegrityCaseRepository caseRepo;
    private final RepeatOffenderRecordRepository repeatRepo;
    private final RepeatOffenderCalculator calculator;

    public StudentProfileServiceImpl(
            StudentProfileRepository studentRepo,
            IntegrityCaseRepository caseRepo,
            RepeatOffenderRecordRepository repeatRepo,
            RepeatOffenderCalculator calculator) {
        this.studentRepo = studentRepo;
        this.caseRepo = caseRepo;
        this.repeatRepo = repeatRepo;
        this.calculator = calculator;
    }

    public StudentProfile createStudent(StudentProfile student) {
        return studentRepo.save(student);
    }

    public StudentProfile getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }

    public StudentProfile updateRepeatOffenderStatus(Long studentId) {
        StudentProfile student = studentRepo.findById(studentId).orElse(null);
        if (student == null) return null;

        List<IntegrityCase> cases = caseRepo.findByStudentProfile(student);
        student.setIsRepeatOffender(cases.size() >= 2);
        return studentRepo.save(student);
    }

    public StudentProfile findByStudentIdentifier(String studentId) {
        return studentRepo.findByStudentIdentifier(studentId);
    }
}
