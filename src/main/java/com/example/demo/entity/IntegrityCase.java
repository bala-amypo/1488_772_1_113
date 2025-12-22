package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class IntegrityCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private StudentProfile studentProfile;

    @NotBlank
    private String courseCode;

    private String instructorName;
    private String description;
    private String status = "OPEN";
    private LocalDate incidentDate;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<EvidenceRecord> evidences;

    @OneToMany(mappedBy = "integrityCase", cascade = CascadeType.ALL)
    private List<PenaltyAction> penalties;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) { this.studentProfile = studentProfile; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<EvidenceRecord> getEvidences() { return evidences; }
    public void setEvidences(List<EvidenceRecord> evidences) { this.evidences = evidences; }
    public List<PenaltyAction> getPenalties() { return penalties; }
    public void setPenalties(List<PenaltyAction> penalties) { this.penalties = penalties; }
}
