package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Integer age;

    public StudentProfile() {}

    public StudentProfile(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.age = age;
    }

    // getters & setters
}
