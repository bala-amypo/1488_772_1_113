package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.PenaltyAction;

public interface PenaltyActionService {
    PenaltyAction addPenalty(PenaltyAction p);
    List<PenaltyAction> getAllPenalties();
}
