package com.example.timesheet.service.impl;

import com.example.timesheet.entity.User;
import com.example.timesheet.enums.UserRole;
import com.example.timesheet.mapper.LoginResponseMapper;
import com.example.timesheet.repository.UserRepository;
import com.example.timesheet.service.UserService;
import com.example.timesheet.util.jwtUtil;
import com.example.timesheet.dto.LoginResponseDTO;
import com.example.timesheet.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtils;

    @Override
    public User getById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(UserRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(
                request.getRole() != null ? request.getRole() : UserRole.EMPLOYEE
        );
        user.setActive(true);

        return userRepository.save(user);
    }

    @Override
    public LoginResponseDTO validateUser(String username, String password) {
        User user = getByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        String token = jwtUtils.generateToken(user.getUsername());

        return LoginResponseMapper.toResponse(token, user);
    }
}
