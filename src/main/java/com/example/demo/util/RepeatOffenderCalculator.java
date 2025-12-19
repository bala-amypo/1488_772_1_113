package com.example.demo.util;

import java.util.List;
import com.example.demo.entity.*;

public class RepeatOffenderCalculator {

    public RepeatOffenderRecord computeRepeatOffenderRecord(
            StudentProfile student,
            List<IntegrityCase> cases) {

        RepeatOffenderRecord record = new RepeatOffenderRecord();
        record.setStudentProfile(student);
        record.setTotalCases(cases.size());

        if (cases.size() == 1) {
            record.setFlagSeverity("LOW");
        } else if (cases.size() == 2) {
            record.setFlagSeverity("MEDIUM");
        } else if (cases.size() >= 4) {
            record.setFlagSeverity("HIGH");
        }

        record.setLastIncidentDate(
                cases.get(cases.size() - 1).getIncidentDate()
        );

        return record;
    }
}
