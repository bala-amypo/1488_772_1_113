package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence_records")
public class EvidenceRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "integrity_case_id", nullable = false)
    private IntegrityCase integrityCase;
    
    @Column(name = "evidence_type")
    private String evidenceType;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "submitted_by")
    private String submittedBy;
    
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
    
    public EvidenceRecord() {
        this.submittedAt = LocalDateTime.now();
    }
    
    public EvidenceRecord(IntegrityCase integrityCase, String evidenceType, String content, String submittedBy) {
        this.integrityCase = integrityCase;
        this.evidenceType = evidenceType;
        this.content = content;
        this.submittedBy = submittedBy;
        this.submittedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public IntegrityCase getIntegrityCase() { return integrityCase; }
    public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }
    public String getEvidenceType() { return evidenceType; }
    public void setEvidenceType(String evidenceType) { this.evidenceType = evidenceType; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}