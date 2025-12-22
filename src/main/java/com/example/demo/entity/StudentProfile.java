package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "student_id", unique = true)
    private String studentId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    private String program;
    
    @Column(name = "year_level", nullable = false)
    @NotNull(message = "Year level cannot be null")
    private Integer yearLevel;
    
    @Column(name = "repeat_offender")
    private Boolean repeatOffender = false;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private List<IntegrityCase> integrityCases = new ArrayList<>();
    
    @OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    private List<RepeatOffenderRecord> repeatOffenderRecords = new ArrayList<>();
    
    // Constructors
    public StudentProfile() {
        this.createdAt = LocalDateTime.now();
        this.repeatOffender = false;
    }
    
    public StudentProfile(String studentId, String name, String email, String program, Integer yearLevel) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.program = program;
        this.yearLevel = yearLevel;
        this.repeatOffender = false;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters (same as before)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    
    public Integer getYearLevel() { return yearLevel; }
    public void setYearLevel(Integer yearLevel) { 
        this.yearLevel = yearLevel; 
    }
    
    public Boolean getRepeatOffender() { return repeatOffender; }
    public void setRepeatOffender(Boolean repeatOffender) { this.repeatOffender = repeatOffender; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<IntegrityCase> getIntegrityCases() { return integrityCases; }
    public void setIntegrityCases(List<IntegrityCase> integrityCases) { this.integrityCases = integrityCases; }
    
    public List<RepeatOffenderRecord> getRepeatOffenderRecords() { return repeatOffenderRecords; }
    public void setRepeatOffenderRecords(List<RepeatOffenderRecord> repeatOffenderRecords) { 
        this.repeatOffenderRecords = repeatOffenderRecords; 
    }
    
    public void addIntegrityCase(IntegrityCase integrityCase) {
        integrityCases.add(integrityCase);
        integrityCase.setStudentProfile(this);
    }
}