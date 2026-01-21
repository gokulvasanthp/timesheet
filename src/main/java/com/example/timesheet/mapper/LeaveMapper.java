package com.example.timesheet.mapper;

import com.example.timesheet.dto.LeaveResponseDTO;
import com.example.timesheet.entity.Leave;

public class LeaveMapper {

    private LeaveMapper() {
    }

    public static LeaveResponseDTO toResponse(Leave leave) {
        if (leave == null) {
            return null;
        }

        return new LeaveResponseDTO(
                leave.getId(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getLeaveType().name(),
                leave.getStatus().name(),
                leave.getReason(),
                UserMapper.toResponse(leave.getUser()),
                leave.getCreatedAt(),
                leave.getUpdatedAt()
        );
    }
}
