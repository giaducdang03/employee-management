package com.ducdpg.employee_demo.repositories;

import com.ducdpg.employee_demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    Department findByName(String name);
}
