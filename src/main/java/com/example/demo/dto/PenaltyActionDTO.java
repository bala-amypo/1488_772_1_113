package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class PenaltyActionDTO {

    private Long id;

    @NotBlank
    private String penaltyType;

    private String description;

    private LocalDate issuedDate;

    @NotNull
    private Long integrityCaseId;

    // getters and setters
}
