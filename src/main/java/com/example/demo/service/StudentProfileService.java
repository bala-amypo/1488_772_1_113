package com.example.demo.service;

import com.example.demo.model.StudentProfile;
import java.util.List;
import java.util.Optional;

public interface StudentProfileService {

    StudentProfile saveStudent(StudentProfile student);

    List<StudentProfile> getAllStudents();

    Optional<StudentProfile> getStudentById(Long id);

    StudentProfile updateStudent(Long id, StudentProfile student);

    void deleteStudent(Long id);
}
