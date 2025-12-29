package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    private Integer totalCases;
    private LocalDate firstIncidentDate;
    private String flagSeverity;

    public RepeatOffenderRecord() {
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
    
    public Integer getTotalCases() {
        return totalCases;
    }
    
    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }
    
    public LocalDate getFirstIncidentDate() {
        return firstIncidentDate;
    }
    
    public void setFirstIncidentDate(LocalDate firstIncidentDate) {
        this.firstIncidentDate = firstIncidentDate;
    }
    
    public String getFlagSeverity() {
        return flagSeverity;
    }
    
    public void setFlagSeverity(String flagSeverity) {
        this.flagSeverity = flagSeverity;
    }
}

