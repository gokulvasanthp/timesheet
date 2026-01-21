 package com.example.timesheet.entity;

import com.example.timesheet.common.BaseEntity;
import com.example.timesheet.enums.LeaveType;
import com.example.timesheet.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
    name = "leaves",
    indexes = {
        @Index(name = "idx_leave_user", columnList = "user_id"),
        @Index(name = "idx_leave_status", columnList = "status")
    }
)
@Getter
@Setter
public class Leave extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LeaveStatus status = LeaveStatus.PENDING;

    @Column(length = 500)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;
}
