package com.example.demo.util;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class RepeatOffenderCalculator {

    public RepeatOffenderRecord computeRepeatOffenderRecord(
            StudentProfile studentProfile,
            List<IntegrityCase> cases
    ) {
        int totalCases = calculateTotalCases(cases);
        String severity = calculateSeverity(totalCases);
        LocalDate firstIncidentDate = findFirstIncidentDate(cases);

        RepeatOffenderRecord record = new RepeatOffenderRecord();
        record.setStudentProfile(studentProfile);
        record.setTotalCases(totalCases);
        record.setFlagSeverity(severity);
        record.setFirstIncidentDate(firstIncidentDate);

        return record;
    }

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
