package com.example.timesheet.entity;

import com.example.timesheet.common.BaseEntity;
import com.example.timesheet.enums.TimesheetStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
    name = "timesheets",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "work_date"})
    },
    indexes = {
        @Index(name = "idx_timesheet_user", columnList = "user_id")
    }
)
@Getter
@Setter
public class Timesheet extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(nullable = false)
    private Double hoursWorked;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimesheetStatus status = TimesheetStatus.SUBMITTED;
}
