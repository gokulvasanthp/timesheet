package com.example.timesheet.service;

import com.example.timesheet.entity.User;
import com.example.timesheet.dto.UserRequestDTO;

import java.util.UUID;

public interface UserService {

    User getById(UUID userId);

    User getByUsername(String username);

    User createUser(UserRequestDTO request);
}
