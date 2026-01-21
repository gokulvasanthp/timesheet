package com.example.timesheet.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String email,
        String role
) {}
