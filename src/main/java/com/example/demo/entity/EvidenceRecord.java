package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "evidence_records")
public class EvidenceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Rule: Each evidence must link to a valid IntegrityCase
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "integrity_case_id", nullable = false)
    private IntegrityCase integrityCase;

    // Rule: evidenceType (String: TEXT / FILE / LINK)
    @Column(nullable = false)
    private String evidenceType;

    // Rule: content (String) - Cannot be empty
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String submittedBy;

    private LocalDateTime submittedAt;

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
    }
}