package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentProfileController.class)
class StudentProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentProfileService studentProfileService;

    @Test
    void createStudent_Success() throws Exception {
        StudentProfile student = new StudentProfile("S001", "John Doe", "john@example.com", "CS", 3);
        student.setId(1L);
        
        when(studentProfileService.createStudent(any(StudentProfile.class))).thenReturn(student);
        
        String studentJson = """
            {
                "studentId": "S001",
                "name": "John Doe",
                "email": "john@example.com",
                "program": "CS",
                "yearLevel": 3
            }
            """;
        
        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Student created successfully"));
    }

    @Test
    void getAllStudents_Success() throws Exception {
        StudentProfile student1 = new StudentProfile("S001", "John Doe", "john@example.com", "CS", 3);
        student1.setId(1L);
        StudentProfile student2 = new StudentProfile("S002", "Jane Smith", "jane@example.com", "EE", 2);
        student2.setId(2L);
        
        List<StudentProfile> students = Arrays.asList(student1, student2);
        
        when(studentProfileService.getAllStudents()).thenReturn(students);
        
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.count").value(2));
    }
}