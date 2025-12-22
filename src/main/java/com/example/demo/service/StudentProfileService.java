package com.example.demo.service;

import com.example.demo.entity.StudentProfile;

import java.util.List;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfile student);
    List<StudentProfile> getAllStudents();
    StudentProfile getStudentById(Long id);
}
