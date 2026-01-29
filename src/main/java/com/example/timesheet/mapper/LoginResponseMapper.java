package com.example.timesheet.mapper;
import com.example.timesheet.dto.LoginResponseDTO;
import com.example.timesheet.entity.User;

public class LoginResponseMapper {

    private LoginResponseMapper() {
        // utility class
    }

    public static LoginResponseDTO toResponse(
            String token,
            User user
    ) {
        if (user == null || token == null) {
            return null;
        }

        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setToken(token);
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());

        return dto;
    }
}