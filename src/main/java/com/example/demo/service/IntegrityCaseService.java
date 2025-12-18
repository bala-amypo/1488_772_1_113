package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.IntegrityCase;

public interface IntegrityCaseService {
    IntegrityCase createCase(IntegrityCase c);
    List<IntegrityCase> getAllCases();
}
