package com.example.timesheet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import com.example.timesheet.enums.UserRole;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private UserRole role; // optional
}
