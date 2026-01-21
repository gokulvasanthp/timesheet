package com.example.timesheet.dto;

import com.example.timesheet.enums.LeaveType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LeaveRequestDTO(

        @NotNull
        LeaveType leaveType,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate,

        @NotNull
        BigDecimal days,

        String reason
) {}
