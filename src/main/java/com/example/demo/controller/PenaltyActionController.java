package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.PenaltyAction;
import com.example.demo.service.PenaltyActionService;

@RestController
@RequestMapping("/penalties")
public class PenaltyActionController {

    private final PenaltyActionService service;

    public PenaltyActionController(PenaltyActionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public PenaltyAction add(@RequestBody PenaltyAction p) {
        return service.addPenalty(p);
    }

    @GetMapping("/show")
    public List<PenaltyAction> show() {
        return service.getAllPenalties();
    }
}
