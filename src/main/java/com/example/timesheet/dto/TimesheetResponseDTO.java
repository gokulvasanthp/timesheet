package com.example.timesheet.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record TimesheetResponseDTO(
        UUID id,
        LocalDate workDate,
        Double hoursWorked,
        String description,
        String status,
        UserResponseDTO user,
        Instant createdAt,
        Instant updatedAt
) {}
