package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.repository.IntegrityCaseRepository;

@Service
public class IntegrityCaseServiceImpl implements IntegrityCaseService {

    private final IntegrityCaseRepository repo;

    public IntegrityCaseServiceImpl(IntegrityCaseRepository repo) {
        this.repo = repo;
    }

    @Override
    public IntegrityCase createCase(IntegrityCase c) {
        c.setCreatedAt(LocalDateTime.now());
        if (c.getStatus() == null) {
            c.setStatus("OPEN");
        }
        return repo.save(c);
    }

    @Override
    public List<IntegrityCase> getAllCases() {
        return repo.findAll();
    }
}
