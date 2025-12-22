package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String studentId;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    private String program;
    private Integer yearLevel;
    private Boolean isRepeatOffender = false;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private List<IntegrityCase> integrityCases;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public Integer getYearLevel() { return yearLevel; }
    public void setYearLevel(Integer yearLevel) { this.yearLevel = yearLevel; }
    public Boolean getIsRepeatOffender() { return isRepeatOffender; }
    public void setIsRepeatOffender(Boolean repeatOffender) { isRepeatOffender = repeatOffender; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<IntegrityCase> getIntegrityCases() { return integrityCases; }
    public void setIntegrityCases(List<IntegrityCase> integrityCases) { this.integrityCases = integrityCases; }
}
