package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import java.util.List;
import java.util.Optional;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfile studentProfile);
    StudentProfile getStudentById(Long id);
    List<StudentProfile> getAllStudents();
    StudentProfile updateRepeatOffenderStatus(Long studentId);
    Optional<StudentProfile> findByStudentId(String studentId);
    StudentProfile updateStudent(Long id, StudentProfile studentProfile);
    void deleteStudent(Long id);
}