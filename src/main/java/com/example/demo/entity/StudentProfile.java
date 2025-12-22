package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String studentId;

    private String name;

    @Column(unique = true)
    private String email;

    private String program;
    private Integer yearLevel;
    private Boolean isRepeatOffender = false;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "studentProfile")
    private List<IntegrityCase> integrityCases;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public StudentProfile() {}

    // getters and setters
    public Long getId() { return id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getIsRepeatOffender() { return isRepeatOffender; }
    public void setIsRepeatOffender(Boolean isRepeatOffender) { this.isRepeatOffender = isRepeatOffender; }
}
