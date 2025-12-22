package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "penalty_actions")
public class PenaltyAction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name = "integrity_case_id", nullable = false) private IntegrityCase integrityCase;
    @Column(name = "penalty_type") private String penaltyType;
    @Column(columnDefinition = "TEXT") private String details;
    @Column(name = "issued_by") private String issuedBy;
    @Column(name = "issued_at", nullable = false) private LocalDateTime issuedAt;
    
    public PenaltyAction() { this.issuedAt = LocalDateTime.now(); }
    public PenaltyAction(IntegrityCase integrityCase, String penaltyType, String details, String issuedBy) {
        this.integrityCase = integrityCase; this.penaltyType = penaltyType; 
        this.details = details; this.issuedBy = issuedBy; this.issuedAt = LocalDateTime.now();
    }
    
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public IntegrityCase getIntegrityCase() { return integrityCase; } public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }
    public String getPenaltyType() { return penaltyType; } public void setPenaltyType(String penaltyType) { this.penaltyType = penaltyType; }
    public String getDetails() { return details; } public void setDetails(String details) { this.details = details; }
    public String getIssuedBy() { return issuedBy; } public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }
    public LocalDateTime getIssuedAt() { return issuedAt; } public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
}