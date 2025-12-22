package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    public StudentProfile create(@RequestBody StudentProfile studentProfile) {
        return service.save(studentProfile);
    }

    @GetMapping("/{id}")
    public StudentProfile get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<StudentProfile> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public StudentProfile update(@PathVariable Long id,
                                 @RequestBody StudentProfile studentProfile) {
        return service.update(id, studentProfile);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
