package com.example.demo.repository;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IntegrityCaseRepository extends JpaRepository<IntegrityCase, Long> {
    List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);
    List<IntegrityCase> findByIncidentDateBetween(LocalDate start, LocalDate end);
}
