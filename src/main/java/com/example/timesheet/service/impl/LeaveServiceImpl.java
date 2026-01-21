package com.example.timesheet.service.impl;

import com.example.timesheet.dto.LeaveRequestDTO;
import com.example.timesheet.dto.LeaveResponseDTO;
import com.example.timesheet.entity.Leave;
import com.example.timesheet.entity.User;
import com.example.timesheet.enums.LeaveStatus;
import com.example.timesheet.mapper.LeaveMapper;
import com.example.timesheet.repository.LeaveRepository;
import com.example.timesheet.service.LeaveService;
import com.example.timesheet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final UserService userService;

    @Override
    public LeaveResponseDTO applyLeave(UUID userId, LeaveRequestDTO request) {

        Leave leave = new Leave();
        leave.setUser(userService.getById(userId));
        leave.setStartDate(request.startDate());
        leave.setEndDate(request.endDate());
        leave.setLeaveType(request.leaveType());
        leave.setReason(request.reason());

        // Approval required
        leave.setStatus(LeaveStatus.PENDING);

        return LeaveMapper.toResponse(
                leaveRepository.save(leave)
        );
    }

    @Override
    public List<LeaveResponseDTO> getUserLeaves(UUID userId) {
        return leaveRepository.findByUserId(userId)
                .stream()
                .map(LeaveMapper::toResponse)
                .toList();
    }

    @Override
    public LeaveResponseDTO approveLeave(UUID leaveId, UUID approverId) {
        Leave leave = getLeaveOrThrow(leaveId);
        User approver = userService.getById(approverId);

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setApprovedBy(approver);

        return LeaveMapper.toResponse(
                leaveRepository.save(leave)
        );
    }

    @Override
    public LeaveResponseDTO rejectLeave(UUID leaveId, UUID approverId) {
        Leave leave = getLeaveOrThrow(leaveId);
        User approver = userService.getById(approverId);

        leave.setStatus(LeaveStatus.REJECTED);
        leave.setApprovedBy(approver);

        return LeaveMapper.toResponse(
                leaveRepository.save(leave)
        );
    }

    private Leave getLeaveOrThrow(UUID leaveId) {
        return leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
    }
}
