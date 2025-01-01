package com.ducdpg.employee_demo.services;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.entity.Employee;
import com.ducdpg.employee_demo.models.employee.EmployeeCreateModel;
import com.ducdpg.employee_demo.models.employee.EmployeeModel;
import com.ducdpg.employee_demo.models.employee.EmployeeUpdateModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Page<EmployeeModel> getAll(int page, int size, String departmentId, String name, String[] sort);
    EmployeeModel getById(String id);
    EmployeeModel save(EmployeeCreateModel employee);
    EmployeeModel update(EmployeeUpdateModel employee);
    void softDeleteById(String id);
}
