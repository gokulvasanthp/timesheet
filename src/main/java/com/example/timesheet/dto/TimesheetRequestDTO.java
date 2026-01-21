package com.example.timesheet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TimesheetRequestDTO(

        @NotNull
        LocalDate workDate,

        UUID projectId,

        @NotNull
        @DecimalMin("0.5")
        BigDecimal hours,

        String taskDescription
) {}
