package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RepeatOffenderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    private Integer totalCases;
    private LocalDate lastIncidentDate;
    private String flagSeverity;

    // Getters & Setters
    public Long getId() { return id; }
    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) { this.studentProfile = studentProfile; }
    public Integer getTotalCases() { return totalCases; }
    public void setTotalCases(Integer totalCases) { this.totalCases = totalCases; }
    public LocalDate getLastIncidentDate() { return lastIncidentDate; }
    public void setLastIncidentDate(LocalDate lastIncidentDate) { this.lastIncidentDate = lastIncidentDate; }
    public String getFlagSeverity() { return flagSeverity; }
    public void setFlagSeverity(String flagSeverity) { this.flagSeverity = flagSeverity; }
}
