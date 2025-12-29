
package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    public ResponseEntity<StudentProfile> create(
            @RequestBody StudentProfile studentProfile) {

        return ResponseEntity.ok(
                studentProfileService.createStudent(studentProfile)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentProfileService.getStudentById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(
                studentProfileService.getAllStudents()
        );
    }

    @PutMapping("/{id}/repeat-status")
    public ResponseEntity<StudentProfile> updateRepeatStatus(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentProfileService.updateRepeatOffenderStatus(id)
        );
    }
}
