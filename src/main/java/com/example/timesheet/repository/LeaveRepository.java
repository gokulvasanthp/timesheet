package com.example.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.timesheet.entity.Leave;
import com.example.timesheet.enums.LeaveStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LeaveRepository extends JpaRepository<Leave, UUID> {

    List<Leave> findByUserId(UUID userId);

    List<Leave> findByStatus(LeaveStatus status);

    List<Leave> findByUserIdAndStatus(UUID userId, LeaveStatus status);

    List<Leave> findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID userId,
            LocalDate endDate,
            LocalDate startDate
    );
}
