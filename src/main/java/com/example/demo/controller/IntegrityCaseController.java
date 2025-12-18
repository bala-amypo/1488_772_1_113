package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.IntegrityCase;
import com.example.demo.service.IntegrityCaseService;

@RestController
@RequestMapping("/cases")
public class IntegrityCaseController {

    private final IntegrityCaseService service;

    public IntegrityCaseController(IntegrityCaseService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public IntegrityCase add(@RequestBody IntegrityCase c) {
        return service.createCase(c);
    }

    @GetMapping("/show")
    public List<IntegrityCase> show() {
        return service.getAllCases();
    }
}
