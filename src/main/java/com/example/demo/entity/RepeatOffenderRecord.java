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

    public RepeatOffenderRecord() {}

    public Long getId() { return id; }
    public void setFlagSeverity(String flagSeverity) { this.flagSeverity = flagSeverity; }
}
