package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IntegrityCaseDTO {

    private Long id;

    @NotBlank
    private String caseType;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

    @NotNull
    private Long studentId;

    // getters and setters
}
