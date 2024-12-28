package com.ducdpg.employee_demo.services;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.entity.Employee;
import com.ducdpg.employee_demo.models.employee.EmployeeCreateModel;
import com.ducdpg.employee_demo.models.employee.EmployeeModel;
import com.ducdpg.employee_demo.models.employee.EmployeeUpdateModel;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<EmployeeModel> getAll();
    EmployeeModel getById(String id);
    EmployeeModel save(EmployeeCreateModel employee);
    EmployeeModel update(EmployeeUpdateModel employee);
    void softDeleteById(String id);
}
