package com.example.timesheet.controller;

import com.example.timesheet.dto.LoginRequestDTO;
import com.example.timesheet.dto.LoginResponseDTO;
import com.example.timesheet.dto.UserRequestDTO;
import com.example.timesheet.dto.UserResponseDTO;
import com.example.timesheet.entity.User;
import com.example.timesheet.mapper.UserMapper;
import com.example.timesheet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> auth(@Valid @RequestBody LoginRequestDTO request) {
        //User user = userService.getByUsername(request.getUsername());
        return ResponseEntity.ok(userService.validateUser(request.getUsername(), request.getPassword()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(
                UserMapper.toResponse(userService.getById(userId))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO request) {

        User user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toResponse(user));
    }
}
