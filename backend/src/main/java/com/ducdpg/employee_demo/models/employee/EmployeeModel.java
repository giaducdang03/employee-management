package com.ducdpg.employee_demo.models.employee;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeModel {
    public String id;

    public String fullName;

    public String email;

    public String phoneNumber;

    public String address;

    public double baseSalary;

    public LocalDateTime dateOfBirth;

    public LocalDateTime hireDate;

    public String jobTitle;

    public String departmentName;

    public String departmentId;

    public LocalDateTime createdDate;

    public LocalDateTime updatedDate;

    public boolean isDelete;
}
