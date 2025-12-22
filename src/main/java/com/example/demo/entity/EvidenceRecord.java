package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class EvidenceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private IntegrityCase integrityCase;

    @NotBlank
    private String evidenceType;

    @NotBlank
    private String content;

    private String submittedBy;
    private LocalDateTime submittedAt;

    @PrePersist
    public void prePersist() {
        submittedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public IntegrityCase getIntegrityCase() { return integrityCase; }
    public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }
    public String getEvidenceType() { return evidenceType; }
    public void setEvidenceType(String evidenceType) { this.evidenceType = evidenceType; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
}
