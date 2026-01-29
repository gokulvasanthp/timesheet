package com.example.timesheet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    @NotBlank
    private String token;

    @NotBlank
    private String username;

    @NotBlank
    private String role;
}
