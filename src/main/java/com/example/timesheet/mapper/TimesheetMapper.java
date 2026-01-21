package com.example.timesheet.mapper;

import com.example.timesheet.dto.TimesheetResponseDTO;
import com.example.timesheet.entity.Timesheet;

public class TimesheetMapper {

    private TimesheetMapper() {
    }

    public static TimesheetResponseDTO toResponse(Timesheet timesheet) {
        if (timesheet == null) {
            return null;
        }

        return new TimesheetResponseDTO(
                timesheet.getId(),
                timesheet.getWorkDate(),
                timesheet.getHoursWorked(),
                timesheet.getDescription(),
                timesheet.getStatus().name(),
                UserMapper.toResponse(timesheet.getUser()),
                timesheet.getCreatedAt(),
                timesheet.getUpdatedAt()
        );
    }
}
