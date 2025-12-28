package com.example.demo.util;

import com.example.demo.entity.IntegrityCase;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class RepeatOffenderCalculator {

    public int calculateTotalCases(List<IntegrityCase> cases) {
        return cases == null ? 0 : cases.size();
    }

    public String calculateSeverity(int totalCases) {
        if (totalCases <= 1) {
            return "LOW";
        } else if (totalCases == 2 || totalCases == 3) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }

    public LocalDate findFirstIncidentDate(List<IntegrityCase> cases) {
        if (cases == null || cases.isEmpty()) {
            return null;
        }

        return cases.stream()
                .map(IntegrityCase::getIncidentDate)
                .filter(date -> date != null)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }
}
