package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.*;
import com.example.demo.util.RepeatOffenderCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentRepo;

    public StudentProfileServiceImpl(
            StudentProfileRepository studentRepo,
            IntegrityCaseRepository c,
            RepeatOffenderRecordRepository r,
            RepeatOffenderCalculator calc) {
        this.studentRepo = studentRepo;
    }

    public StudentProfile createStudent(StudentProfile student) {
        return studentRepo.save(student);
    }

    public List<StudentProfile> getAllStudents() {
        return studentRepo.findAll();
    }
}
