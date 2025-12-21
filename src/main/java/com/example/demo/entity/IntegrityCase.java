package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "integrity_cases")
public class IntegrityCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
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
    private List<PenaltyAction> penaltyActions = new ArrayList<>();
}
public StudentProfile getStudentProfile() {
    return studentProfile;
}

public void setStatus(String status) {
    this.status = status;
}

public LocalDate getIncidentDate() {
    return incidentDate;
}

