package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence_records")
public class EvidenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private IntegrityCase integrityCase;

    private String evidenceType;
    private String content;
    private String submittedBy;
    private LocalDateTime submittedAt = LocalDateTime.now();

    public EvidenceRecord() {
        this.submittedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public IntegrityCase getIntegrityCase() {
        return integrityCase;
    }
    
    public void setIntegrityCase(IntegrityCase integrityCase) {
        this.integrityCase = integrityCase;
    }
    
    public String getEvidenceType() {
        return evidenceType;
    }
    
    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSubmittedBy() {
        return submittedBy;
    }
    
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
    
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
