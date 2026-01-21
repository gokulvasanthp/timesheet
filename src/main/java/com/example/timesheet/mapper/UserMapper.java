package com.example.timesheet.mapper;

import com.example.timesheet.dto.UserResponseDTO;
import com.example.timesheet.entity.User;

public class UserMapper {

    private UserMapper() {
        // utility class
    }

    public static UserResponseDTO toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
