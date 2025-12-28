package com.example.demo.repository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntegrityCaseRepository extends JpaRepository<IntegrityCase, Long> {

    List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

    List<IntegrityCase> findByStudentProfile_Id(Long studentId);

    @Query("""
           SELECT c
           FROM IntegrityCase c
           WHERE c.studentProfile.studentId = :studentIdentifier
           """)
    List<IntegrityCase> findByStudentIdentifier(
            @Param("studentIdentifier") String studentIdentifier);

    @Query("""
           SELECT c
           FROM IntegrityCase c
           WHERE c.status = :status
           AND c.incidentDate >= :sinceDate
           """)
    List<IntegrityCase> findRecentCasesByStatus(
            @Param("status") String status,
            @Param("sinceDate") LocalDate sinceDate);

    List<IntegrityCase> findByIncidentDateBetween(
            LocalDate start,
            LocalDate end);

    List<IntegrityCase> findByStatus(String status);
}