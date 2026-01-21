package com.example.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.timesheet.entity.Timesheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimesheetRepository extends JpaRepository<Timesheet, UUID> {

    Optional<Timesheet> findByUserIdAndWorkDate(UUID userId, LocalDate workDate);

    List<Timesheet> findByUserId(UUID userId);

    List<Timesheet> findByUserIdAndWorkDateBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );
}
