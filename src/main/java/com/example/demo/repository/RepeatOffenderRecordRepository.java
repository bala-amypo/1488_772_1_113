package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepeatOffenderRecordRepository extends JpaRepository<RepeatOffenderRecord, Long> {
    RepeatOffenderRecord findByStudentProfile(StudentProfile s);
}
