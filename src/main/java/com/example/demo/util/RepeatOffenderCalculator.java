
package com.example.demo.util;

import com.example.demo.entity.IntegrityCase;
import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RepeatOffenderCalculator {

    public RepeatOffenderRecord computeRepeatOffenderRecord(
            StudentProfile studentProfile,
            List<IntegrityCase> cases) {

        RepeatOffenderRecord record = new RepeatOffenderRecord();
        record.setStudentProfile(studentProfile);

        int totalCases = cases != null ? cases.size() : 0;
        record.setTotalCases(totalCases);

        if (totalCases > 0) {
            LocalDate earliest = cases.get(0).getIncidentDate();
            for (IntegrityCase c : cases) {
                if (c.getIncidentDate() != null &&
                        (earliest == null || c.getIncidentDate().isBefore(earliest))) {
                    earliest = c.getIncidentDate();
                }
            }
            record.setFirstIncidentDate(earliest);
        }

        if (totalCases <= 1) {
            record.setFlagSeverity("LOW");
        } else if (totalCases <= 3) {
            record.setFlagSeverity("MEDIUM");
        } else {
            record.setFlagSeverity("HIGH");
        }

        return record;
    }
}
