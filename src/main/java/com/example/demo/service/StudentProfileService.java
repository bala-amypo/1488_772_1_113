package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import java.util.List;

public interface StudentProfileService {

    StudentProfile save(StudentProfile studentProfile);

    StudentProfile getById(Long id);

    List<StudentProfile> getAll();

    StudentProfile update(Long id, StudentProfile studentProfile);

    void delete(Long id);
}
