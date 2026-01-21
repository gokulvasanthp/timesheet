package com.example.timesheet.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record LeaveResponseDTO(
        UUID id,
        LocalDate startDate,
        LocalDate endDate,
        String leaveType,
        String status,
        String reason,
        UserResponseDTO user,
        Instant createdAt,
        Instant updatedAt
) {}
