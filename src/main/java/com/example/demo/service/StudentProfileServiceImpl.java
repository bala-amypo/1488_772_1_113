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
        // Data validation
        if (studentProfile.getYearLevel() == null) {
            throw new IllegalArgumentException("Year level cannot be null");
        }
        
        if (studentProfile.getName() == null || studentProfile.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        
        if (studentProfile.getEmail() == null || studentProfile.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        
        // Set default values
        if (studentProfile.getRepeatOffender() == null) {
            studentProfile.setRepeatOffender(false);
        }
        
        return studentProfileRepository.save(studentProfile);
    }
    
    @Override
    @Transactional(readOnly = true)
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }
    
    @Override
    public StudentProfile updateRepeatOffenderStatus(Long studentId) {
        StudentProfile studentProfile = getStudentById(studentId);
        
        // Simple logic for demonstration
        // In real implementation, you'd count integrity cases
        long caseCount = studentProfile.getIntegrityCases().size();
        
        if (caseCount >= 2) {
            studentProfile.setRepeatOffender(true);
        } else {
            studentProfile.setRepeatOffender(false);
        }
        
        return studentProfileRepository.save(studentProfile);
    }
}