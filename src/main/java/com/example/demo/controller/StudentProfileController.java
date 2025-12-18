package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public StudentProfile add(@RequestBody StudentProfile s) {
        return service.createStudent(s);
    }

    @GetMapping("/show")
    public List<StudentProfile> show() {
        return service.getAllStudents();
    }
}
