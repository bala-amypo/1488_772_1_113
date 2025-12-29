package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "integrity_cases")
public class IntegrityCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    private String courseCode;
    private String instructorName;
    private String description;
    private String status = "OPEN";
    private LocalDate incidentDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<EvidenceRecord> evidenceRecords = new ArrayList<>();

    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<PenaltyAction> penalties = new ArrayList<>();

    public IntegrityCase() {
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public StudentProfile getStudentProfile() {
        return studentProfile;
    }
    
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getIncidentDate() {
        return incidentDate;
    }
    
    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<EvidenceRecord> getEvidenceRecords() {
        return evidenceRecords;
    }
    
    public List<PenaltyAction> getPenalties() {
        return penalties;
    }
}

