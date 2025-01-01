package com.ducdpg.employee_demo.models.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeCreateModel {


    @NotBlank(message = "Full name is required")
    public String fullName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    public String email;

    @NotBlank(message = "Phone number is required")
    public String phoneNumber;

    @NotBlank(message = "Address is required")
    public String address;

    @Min(value = 0, message = "Base salary must be greater than or equal to 0")
    public double baseSalary;

    @NotNull(message = "Date of birth is required")
    public LocalDateTime dateOfBirth;

    @NotNull(message = "Hire date is required")
    public LocalDateTime hireDate;

    @NotBlank(message = "Job title is required")
    public String jobTitle;

    @NotBlank(message = "Department ID is required")
    public String departmentId;
}
