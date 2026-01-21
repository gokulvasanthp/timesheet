package com.example.timesheet.service;

import com.example.timesheet.dto.TimesheetRequestDTO;
import com.example.timesheet.dto.TimesheetResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TimesheetService {

    TimesheetResponseDTO createTimesheet(UUID userId, TimesheetRequestDTO request);

    List<TimesheetResponseDTO> getUserTimesheets(UUID userId);
}
