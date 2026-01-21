package com.example.timesheet.entity;

import com.example.timesheet.common.BaseEntity;
import com.example.timesheet.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    },
    indexes = {
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_email", columnList = "email")
    }
)
@Getter
@Setter
public class User extends BaseEntity {

    @Column(nullable = false, length = 100)
    @NotBlank
    private String username;

    @Column(nullable = false, length = 150)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password; // store hashed password only

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.EMPLOYEE;

    @Column(nullable = false)
    private Boolean active = true;

    /* ---------- Relationships ---------- */

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Timesheet> timesheets;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Leave> leaves;
}
