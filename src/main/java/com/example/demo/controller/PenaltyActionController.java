package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyActionController {

    private final PenaltyActionService service;

    public PenaltyActionController(PenaltyActionService service) {
        this.service = service;
    }

    @PostMapping
    public PenaltyAction add(@RequestBody PenaltyAction penalty) {
        return service.addPenalty(penalty);
    }

    @GetMapping("/{id}")
    public PenaltyAction getById(@PathVariable Long id) {
        return service.getPenaltyById(id);
    }

    @GetMapping
    public List<PenaltyAction> getAll() {
        return service.getAllPenalties();
    }
}
