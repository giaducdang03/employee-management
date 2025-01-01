package com.ducdpg.employee_demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, referencedColumnName = "id")
    public Department department;

    @Column(name = "full_name", length = 150, nullable = false)
    public String fullName;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    public String email;

    @Column(name = "phone_number", length = 10, nullable = false)
    public String phoneNumber;

    @Column(name = "address", length = 250, nullable = false)
    public String address;

    @Column(name = "base_salary", nullable = false)
    public double baseSalary;

    @Column(name = "date_of_birth", nullable = false)
    public LocalDateTime dateOfBirth;

    @Column(name = "hire_date", nullable = false)
    public LocalDateTime hireDate;

    @Column(name = "job_title", length = 150, nullable = false)
    public String jobTitle;

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    public String id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    public LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    @Column(name = "is_delete", nullable = false)
    public boolean isDelete;
}
