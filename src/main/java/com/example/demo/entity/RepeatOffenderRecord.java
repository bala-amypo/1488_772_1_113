package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "repeat_offender_records")
public class RepeatOffenderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;

    private int totalCases;
    private String flagSeverity;

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public void setFlagSeverity(String flagSeverity) {
        this.flagSeverity = flagSeverity;
    }
}
