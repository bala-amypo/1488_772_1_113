package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repo;
    private final IntegrityCaseRepository caseRepo;

    public StudentProfileServiceImpl(StudentProfileRepository repo,
                                     IntegrityCaseRepository caseRepo) {
        this.repo = repo;
        this.caseRepo = caseRepo;
    }

    public StudentProfile createStudent(StudentProfile s) {
        if (s.getYearLevel() == null)
            throw new IllegalArgumentException("Year level required");

        s.setRepeatOffender(false);
        return repo.save(s);
    }

    public StudentProfile getStudentById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public java.util.List<StudentProfile> getAllStudents() {
        return repo.findAll();
    }

    public StudentProfile updateRepeatOffenderStatus(Long id) {
        StudentProfile s = getStudentById(id);
        int count = caseRepo.findByStudentProfile_Id(id).size();
        s.setRepeatOffender(count >= 2);
        return repo.save(s);
    }
}
