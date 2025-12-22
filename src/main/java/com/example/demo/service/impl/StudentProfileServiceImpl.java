package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentProfileServiceImpl implements StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;
    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }
    
    @Override
    public StudentProfile createStudent(StudentProfile studentProfile) {
        if (studentProfile.getYearLevel() == null) throw new IllegalArgumentException("Year level cannot be null");
        if (studentProfile.getName() == null || studentProfile.getName().trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (studentProfile.getEmail() == null || studentProfile.getEmail().trim().isEmpty()) throw new IllegalArgumentException("Email cannot be empty");
        return studentProfileRepository.save(studentProfile);
    }
    
    @Override @Transactional(readOnly = true)
    public StudentProfile getStudentById(Long id) {
        return studentProfileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
    
    @Override @Transactional(readOnly = true)
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }
    
    @Override
    public StudentProfile updateRepeatOffenderStatus(Long studentId) {
        StudentProfile student = getStudentById(studentId);
        int caseCount = student.getIntegrityCases().size();
        student.setRepeatOffender(caseCount >= 2);
        return studentProfileRepository.save(student);
    }
    
    @Override @Transactional(readOnly = true)
    public Optional<StudentProfile> findByStudentId(String studentId) {
        return studentProfileRepository.findByStudentId(studentId);
    }
    
    @Override
    public StudentProfile updateStudent(Long id, StudentProfile studentProfile) {
        StudentProfile existing = getStudentById(id);
        existing.setName(studentProfile.getName());
        existing.setEmail(studentProfile.getEmail());
        existing.setProgram(studentProfile.getProgram());
        existing.setYearLevel(studentProfile.getYearLevel());
        existing.setStudentId(studentProfile.getStudentId());
        return studentProfileRepository.save(existing);
    }
    
    @Override
    public void deleteStudent(Long id) {
        StudentProfile student = getStudentById(id);
        studentProfileRepository.delete(student);
    }
}