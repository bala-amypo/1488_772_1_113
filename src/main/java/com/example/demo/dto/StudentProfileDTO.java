package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentProfileDTO {

    private Long id;

    @NotBlank
    private String studentId;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String program;

    @NotNull
    private Integer yearLevel;

    private Boolean repeatOffender;

    // getters and setters
}
