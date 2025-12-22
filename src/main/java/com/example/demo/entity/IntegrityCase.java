package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @JoinColumn(name = "student_profile_id", nullable = false)
    @NotNull(message = "Student profile cannot be null")
    private StudentProfile studentProfile;
    
    @Column(name = "course_code")
    private String courseCode;
    
    @Column(name = "instructor_name")
    private String instructorName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private String status = "OPEN";
    
    @Column(name = "incident_date")
    private LocalDate incidentDate;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<EvidenceRecord> evidenceRecords = new ArrayList<>();
    
    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<PenaltyAction> penaltyActions = new ArrayList<>();
    
    // Constructors
    public IntegrityCase() {
        this.createdAt = LocalDateTime.now();
        this.status = "OPEN";
    }
    
    public IntegrityCase(StudentProfile studentProfile, String courseCode, String instructorName, 
                        String description, LocalDate incidentDate) {
        this.studentProfile = studentProfile;
        this.courseCode = courseCode;
        this.instructorName = instructorName;
        this.description = description;
        this.incidentDate = incidentDate;
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) { 
        this.studentProfile = studentProfile; 
    }
    
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public LocalDate getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<EvidenceRecord> getEvidenceRecords() { return evidenceRecords; }
    public void setEvidenceRecords(List<EvidenceRecord> evidenceRecords) { this.evidenceRecords = evidenceRecords; }
    
    public List<PenaltyAction> getPenaltyActions() { return penaltyActions; }
    public void setPenaltyActions(List<PenaltyAction> penaltyActions) { this.penaltyActions = penaltyActions; }
    
    public void addEvidenceRecord(EvidenceRecord evidenceRecord) {
        evidenceRecords.add(evidenceRecord);
        evidenceRecord.setIntegrityCase(this);
    }
    
    public void addPenaltyAction(PenaltyAction penaltyAction) {
        penaltyActions.add(penaltyAction);
        penaltyAction.setIntegrityCase(this);
        
        // When penalty is added, change status from "OPEN" to "UNDER_REVIEW"
        if ("OPEN".equals(this.status)) {
            this.status = "UNDER_REVIEW";
        }
    }
}