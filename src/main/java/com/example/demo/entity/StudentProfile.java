package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentId;
    private String name;
    private String email;
    private String program;
    private int yearLevel;
    private boolean repeatOffender;
    private LocalDateTime createdAt;

    public StudentProfile() {}
}
