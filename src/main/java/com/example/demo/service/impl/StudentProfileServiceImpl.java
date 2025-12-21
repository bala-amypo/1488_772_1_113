package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfile saveStudent(StudentProfile student) {
        return studentProfileRepository.save(student);
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public Optional<StudentProfile> getStudentById(Long id) {
        return studentProfileRepository.findById(id);
    }

    @Override
    public StudentProfile updateStudent(Long id, StudentProfile studentDetails) {
        StudentProfile existingStudent = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setAge(studentDetails.getAge());
        // Add other fields as needed

        return studentProfileRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        StudentProfile existingStudent = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentProfileRepository.delete(existingStudent);
    }
}
