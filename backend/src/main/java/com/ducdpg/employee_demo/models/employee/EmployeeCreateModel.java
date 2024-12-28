package com.ducdpg.employee_demo.models.employee;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeCreateModel {

    public String fullName;

    public String email;

    public String phoneNumber;

    public String address;

    public double baseSalary;

    public LocalDateTime dateOfBirth;

    public LocalDateTime hireDate;

    public String jobTitle;

    public String departmentId;
}
