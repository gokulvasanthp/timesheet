package com.example.timesheet.controller;

import com.example.timesheet.dto.LeaveRequestDTO;
import com.example.timesheet.dto.LeaveResponseDTO;
import com.example.timesheet.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<LeaveResponseDTO> applyLeave(
            @PathVariable UUID userId,
            @Valid @RequestBody LeaveRequestDTO request) {

        LeaveResponseDTO response = leaveService.applyLeave(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LeaveResponseDTO>> getUserLeaves(
            @PathVariable UUID userId) {

        return ResponseEntity.ok(leaveService.getUserLeaves(userId));
    }

    @PostMapping("/{leaveId}/approve/{approverId}")
    public ResponseEntity<LeaveResponseDTO> approveLeave(
            @PathVariable UUID leaveId,
            @PathVariable UUID approverId) {

        LeaveResponseDTO response = leaveService.approveLeave(leaveId, approverId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{leaveId}/reject/{approverId}")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(
            @PathVariable UUID leaveId,
            @PathVariable UUID approverId) {

        LeaveResponseDTO response = leaveService.rejectLeave(leaveId, approverId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(
            @PathVariable UUID leaveId) {

        return ResponseEntity.ok(leaveService.getLeaveById(leaveId));
    }
}
