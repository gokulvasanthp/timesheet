package com.example.timesheet.controller;

import com.example.timesheet.dto.TimesheetRequestDTO;
import com.example.timesheet.dto.TimesheetResponseDTO;
import com.example.timesheet.service.TimesheetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/timesheets")
@RequiredArgsConstructor
public class TimesheetController {

    private final TimesheetService timesheetService;

    @PostMapping("/user/{userId}")
    public TimesheetResponseDTO createTimesheet(
            @PathVariable UUID userId,
            @Valid @RequestBody TimesheetRequestDTO request) {

        return timesheetService.createTimesheet(userId, request);
    }

    @GetMapping("/user/{userId}")
    public List<TimesheetResponseDTO> getUserTimesheets(
            @PathVariable UUID userId) {

        return timesheetService.getUserTimesheets(userId);
    }
}
