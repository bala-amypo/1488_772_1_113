package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;

@Repository
public interface IntegrityCaseRepository
        extends JpaRepository<IntegrityCase, Long> {

    // ✅ used in RepeatOffenderRecordServiceImpl
    List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

    // ✅ required by project constraint
    List<IntegrityCase> findByStudentIdentifier(String id);

    // ✅ required by project constraint
    List<IntegrityCase> findRecentCasesByStatus(String status, LocalDate date);

    // ✅ required by project constraint
    List<IntegrityCase> findByIncidentDateBetween(
            LocalDate start,
            LocalDate end
    );
}
