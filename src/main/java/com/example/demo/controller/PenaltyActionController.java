package com.example.demo.controller;

import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/penalties")
public class PenaltyActionController {

    private final PenaltyActionService penaltyService;

    public PenaltyActionController(PenaltyActionService penaltyService) {
        this.penaltyService = penaltyService;
    }

    @PostMapping
    public PenaltyAction addPenalty(@RequestBody PenaltyAction penalty) {
        return penaltyService.addPenalty(penalty);
    }

    @GetMapping
    public List<PenaltyAction> getAllPenalties() {
        return penaltyService.getAllPenalties();
    }
}
