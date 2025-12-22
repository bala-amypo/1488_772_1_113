package com.example.demo.service.impl;

import com.example.demo.entity.RepeatOffenderRecord;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RepeatOffenderRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.IntegrityCaseRepository;
import com.example.demo.service.RepeatOffenderRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RepeatOffenderRecordServiceImpl implements RepeatOffenderRecordService {
    private final StudentProfileRepository studentProfileRepository;
    private final IntegrityCaseRepository integrityCaseRepository;
    private final RepeatOffenderRecordRepository repeatOffenderRecordRepository;
    
    public RepeatOffenderRecordServiceImpl(StudentProfileRepository studentProfileRepository,
                                          IntegrityCaseRepository integrityCaseRepository,
                                          RepeatOffenderRecordRepository repeatOffenderRecordRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.integrityCaseRepository = integrityCaseRepository;
        this.repeatOffenderRecordRepository = repeatOffenderRecordRepository;
    }
    
    @Override
    public RepeatOffenderRecord calculateAndSaveRepeatOffenderRecord(Long studentId) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        var cases = integrityCaseRepository.findByStudentProfile_Id(studentId);
        int totalCases = cases.size();
        Optional<LocalDate> firstIncidentDate = cases.stream().map(c -> c.getIncidentDate()).filter(date -> date != null).min(Comparator.naturalOrder());
        String flagSeverity;
        if (totalCases == 0) flagSeverity = "LOW";
        else if (totalCases == 1) flagSeverity = "LOW";
        else if (totalCases == 2 || totalCases == 3) flagSeverity = "MEDIUM";
        else flagSeverity = "HIGH";
        
        student.setRepeatOffender(totalCases >= 2);
        studentProfileRepository.save(student);
        
        Optional<RepeatOffenderRecord> existingRecord = repeatOffenderRecordRepository.findByStudentProfile(student);
        RepeatOffenderRecord record;
        if (existingRecord.isPresent()) {
            record = existingRecord.get();
            record.setTotalCases(totalCases);
            record.setFlagSeverity(flagSeverity);
            firstIncidentDate.ifPresent(record::setFirstIncidentDate);
        } else {
            record = new RepeatOffenderRecord(student, totalCases, firstIncidentDate.orElse(null), flagSeverity);
        }
        return repeatOffenderRecordRepository.save(record);
    }
    
    @Override @Transactional(readOnly = true)
    public Optional<RepeatOffenderRecord> getRecordByStudentId(Long studentId) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return repeatOffenderRecordRepository.findByStudentProfile(student);
    }
    
    @Override @Transactional(readOnly = true)
    public List<RepeatOffenderRecord> getAllRecords() {
        return repeatOffenderRecordRepository.findAll();
    }
    
    @Override
    public void deleteRecord(Long id) {
        RepeatOffenderRecord record = repeatOffenderRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
        repeatOffenderRecordRepository.delete(record);
    }
}