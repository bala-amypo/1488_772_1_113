package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private String name;
    private String email;
    private String program;
    private Integer yearLevel;

    private boolean repeatOffender = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "studentProfile")
    private List<IntegrityCase> integrityCases;

    public Integer getYearLevel() {
        return yearLevel;
    }

    public void setRepeatOffender(boolean repeatOffender) {
        this.repeatOffender = repeatOffender;
    }
}
