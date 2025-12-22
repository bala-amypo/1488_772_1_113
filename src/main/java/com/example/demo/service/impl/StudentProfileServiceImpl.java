package com.example.demo.service.impl;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        return repository.save(studentProfile);
    }

    @Override
    public StudentProfile getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<StudentProfile> getAll() {
        return repository.findAll();
    }

    @Override
    public StudentProfile update(Long id, StudentProfile studentProfile) {
        studentProfile.setId(id);
        return repository.save(studentProfile);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
