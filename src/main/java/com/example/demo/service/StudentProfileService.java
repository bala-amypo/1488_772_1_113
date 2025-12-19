package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.StudentProfile;

public interface StudentProfileService {
    StudentProfile createStudent(StudentProfile s);
    List<StudentProfile> getAllStudents();
}

