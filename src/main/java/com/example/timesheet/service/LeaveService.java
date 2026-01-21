package com.example.timesheet.service;

import com.example.timesheet.dto.LeaveRequestDTO;
import com.example.timesheet.dto.LeaveResponseDTO;

import java.util.List;
import java.util.UUID;

public interface LeaveService {

    LeaveResponseDTO applyLeave(UUID userId, LeaveRequestDTO request);

    List<LeaveResponseDTO> getUserLeaves(UUID userId);

    LeaveResponseDTO approveLeave(UUID leaveId, UUID approverId);

    LeaveResponseDTO rejectLeave(UUID leaveId, UUID approverId);
}
