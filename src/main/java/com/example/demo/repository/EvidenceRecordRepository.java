package com.example.demo.repository;

import com.example.demo.entity.EvidenceRecord;
import com.example.demo.entity.IntegrityCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenceRecordRepository extends JpaRepository<EvidenceRecord, Long> {
    List<EvidenceRecord> findByIntegrityCase(IntegrityCase integrityCase);
}
