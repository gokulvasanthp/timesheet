package com.example.timesheet.service.impl;

import com.example.timesheet.dto.TimesheetRequestDTO;
import com.example.timesheet.dto.TimesheetResponseDTO;
import com.example.timesheet.entity.Timesheet;
import com.example.timesheet.enums.TimesheetStatus;
import com.example.timesheet.mapper.TimesheetMapper;
import com.example.timesheet.repository.TimesheetRepository;
import com.example.timesheet.service.TimesheetService;
import com.example.timesheet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final UserService userService;

    @Override
    public TimesheetResponseDTO createTimesheet(UUID userId, TimesheetRequestDTO request) {

        Timesheet timesheet = new Timesheet();
        timesheet.setUser(userService.getById(userId));
        timesheet.setWorkDate(request.workDate());
        timesheet.setHoursWorked(request.hours().doubleValue());
        timesheet.setDescription(request.taskDescription());

        // No approval flow
        timesheet.setStatus(TimesheetStatus.SUBMITTED);

        return TimesheetMapper.toResponse(
                timesheetRepository.save(timesheet)
        );
    }

    @Override
    public List<TimesheetResponseDTO> getUserTimesheets(UUID userId) {
        return timesheetRepository.findByUserId(userId)
                .stream()
                .map(TimesheetMapper::toResponse)
                .toList();
    }
}
