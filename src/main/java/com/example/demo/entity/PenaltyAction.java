package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class PenaltyAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private IntegrityCase integrityCase;

    @NotBlank
    private String penaltyType;
    private String details;
    private String issuedBy;
    private LocalDateTime issuedAt;

    @PrePersist
    public void prePersist() {
        issuedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public IntegrityCase getIntegrityCase() { return integrityCase; }
    public void setIntegrityCase(IntegrityCase integrityCase) { this.integrityCase = integrityCase; }
    public String getPenaltyType() { return penaltyType; }
    public void setPenaltyType(String penaltyType) { this.penaltyType = penaltyType; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getIssuedBy() { return issuedBy; }
    public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
}
