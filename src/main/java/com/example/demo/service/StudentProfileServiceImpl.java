package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentProfileServiceImpl implements StudentProfileService {
    
    private final StudentProfileRepository studentProfileRepository;
    
    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }
    
    @Override
    public StudentProfile createStudent(StudentProfile studentProfile) {
        // Validation
        if (studentProfile.getYearLevel() == null) {
            throw new IllegalArgumentException("Year level cannot be null");
        }
        if (studentProfile.getName() == null || studentProfile.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (studentProfile.getEmail() == null || studentProfile.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        
        return studentProfileRepository.save(studentProfile);
    }
    
    @Override
    @Transactional(readOnly = true)
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }
    
    @Override
    public StudentProfile updateRepeatOffenderStatus(Long studentId) {
        StudentProfile student = getStudentById(studentId);
        
        // Count cases to determine if repeat offender
        int caseCount = student.getIntegrityCases().size();
        boolean isRepeatOffender = caseCount >= 2;
        
        student.setRepeatOffender(isRepeatOffender);
        return studentProfileRepository.save(student);
    }
}