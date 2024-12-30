package com.ducdpg.employee_demo.repositories;

import com.ducdpg.employee_demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);
//    Page<Employee> findAll(Pageable pageable);
}
