package com.example.timesheet;

import com.example.timesheet.entity.Leave;
import com.example.timesheet.entity.Timesheet;
import com.example.timesheet.entity.User;
import com.example.timesheet.enums.LeaveStatus;
import com.example.timesheet.enums.LeaveType;
import com.example.timesheet.enums.TimesheetStatus;
import com.example.timesheet.enums.UserRole;
import com.example.timesheet.repository.LeaveRepository;
import com.example.timesheet.repository.TimesheetRepository;
import com.example.timesheet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TimesheetRepository timesheetRepository;
    private final LeaveRepository leaveRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //loadSampleData();
    }

    private void loadSampleData() {
        // Create sample users
        User user1 = new User();
        user1.setUsername("john");
        user1.setEmail("john@example.com");
        user1.setPassword(passwordEncoder.encode("password123"));
        user1.setRole(UserRole.EMPLOYEE);
        user1.setActive(true);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("jane_smith");
        user2.setEmail("jane@example.com");
        user2.setPassword(passwordEncoder.encode("password123"));
        user2.setRole(UserRole.MANAGER);
        user2.setActive(true);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("admin_user");
        user3.setEmail("admin@example.com");
        user3.setPassword(passwordEncoder.encode("password123"));
        user3.setRole(UserRole.ADMIN);
        user3.setActive(true);
        userRepository.save(user3);

        // Create sample timesheets
        Timesheet timesheet1 = new Timesheet();
        timesheet1.setUser(user1);
        timesheet1.setWorkDate(LocalDate.of(2026, 1, 20));
        timesheet1.setHoursWorked(8.0);
        timesheet1.setDescription("Backend API development");
        timesheet1.setStatus(TimesheetStatus.SUBMITTED);
        timesheetRepository.save(timesheet1);

        Timesheet timesheet2 = new Timesheet();
        timesheet2.setUser(user1);
        timesheet2.setWorkDate(LocalDate.of(2026, 1, 21));
        timesheet2.setHoursWorked(7.5);
        timesheet2.setDescription("Database optimization");
        timesheet2.setStatus(TimesheetStatus.SUBMITTED);
        timesheetRepository.save(timesheet2);

        Timesheet timesheet3 = new Timesheet();
        timesheet3.setUser(user2);
        timesheet3.setWorkDate(LocalDate.of(2026, 1, 21));
        timesheet3.setHoursWorked(8.0);
        timesheet3.setDescription("Team meetings and code review");
        timesheet3.setStatus(TimesheetStatus.SUBMITTED);
        timesheetRepository.save(timesheet3);

        // Create sample leaves
        Leave leave1 = new Leave();
        leave1.setUser(user1);
        leave1.setStartDate(LocalDate.of(2026, 2, 1));
        leave1.setEndDate(LocalDate.of(2026, 2, 5));
        leave1.setLeaveType(LeaveType.CASUAL);
        leave1.setStatus(LeaveStatus.PENDING);
        leave1.setReason("Personal work");
        leaveRepository.save(leave1);

        Leave leave2 = new Leave();
        leave2.setUser(user2);
        leave2.setStartDate(LocalDate.of(2026, 1, 25));
        leave2.setEndDate(LocalDate.of(2026, 1, 26));
        leave2.setLeaveType(LeaveType.SICK);
        leave2.setStatus(LeaveStatus.APPROVED);
        leave2.setReason("Medical appointment");
        leave2.setApprovedBy(user3);
        leaveRepository.save(leave2);

        Leave leave3 = new Leave();
        leave3.setUser(user3);
        leave3.setStartDate(LocalDate.of(2026, 3, 1));
        leave3.setEndDate(LocalDate.of(2026, 3, 10));
        leave3.setLeaveType(LeaveType.EARNED);
        leave3.setStatus(LeaveStatus.PENDING);
        leave3.setReason("Vacation");
        leaveRepository.save(leave3);

        System.out.println("✓ Sample data loaded successfully!");
    }
}
