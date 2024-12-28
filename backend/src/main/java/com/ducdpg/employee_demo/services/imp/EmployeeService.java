package com.ducdpg.employee_demo.services.imp;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.entity.Employee;
import com.ducdpg.employee_demo.mapper.EmployeeMapper;
import com.ducdpg.employee_demo.models.employee.EmployeeCreateModel;
import com.ducdpg.employee_demo.models.employee.EmployeeModel;
import com.ducdpg.employee_demo.models.employee.EmployeeUpdateModel;
import com.ducdpg.employee_demo.repositories.DepartmentRepository;
import com.ducdpg.employee_demo.repositories.EmployeeRepository;
import com.ducdpg.employee_demo.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeModel> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeMapper.toEmployeeModelList(employeeList);
    }

    @Override
    public EmployeeModel getById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return null;
        }
        return employeeMapper.toEmployeeModel(employee.get());
    }

    @Override
    public EmployeeModel save(EmployeeCreateModel employee) {

        Optional<Department> department = departmentRepository.findById(employee.getDepartmentId());
        if (department.isEmpty()) {
            throw new RuntimeException("Department not found");
        }

        // check email
        Optional<Employee> existEmailEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existEmailEmployee.isPresent()) {
            throw new RuntimeException("Email already exist");
        }

        Employee newEmployee = new Employee();
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setFullName(employee.getFullName());
        newEmployee.setDepartment(department.get());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setPhoneNumber(employee.getPhoneNumber());
        newEmployee.setBaseSalary(employee.getBaseSalary());
        newEmployee.setHireDate(employee.getHireDate());
        newEmployee.setJobTitle(employee.getJobTitle());
        newEmployee.setDateOfBirth(employee.getDateOfBirth());
        newEmployee.setCreatedDate(LocalDateTime.now());
        newEmployee.isDelete = false;

        Employee savedEmployee = employeeRepository.save(newEmployee);

        // Map Employee entity to EmployeeModel
        return employeeMapper.toEmployeeModel(savedEmployee);
    }

    @Override
    public EmployeeModel update(EmployeeUpdateModel employee) {

        Optional<Department> department = departmentRepository.findById(employee.getDepartmentId());
        if (department.isEmpty()) {
            throw new RuntimeException("Department not found");
        }

        Employee updateEmployee = employeeRepository.findById(employee.getId()).get();
        if (updateEmployee == null) {
            throw new RuntimeException("Employee not found");
        }

        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setFullName(employee.getFullName());
        updateEmployee.setDepartment(department.get());
        updateEmployee.setAddress(employee.getAddress());
        updateEmployee.setPhoneNumber(employee.getPhoneNumber());
        updateEmployee.setBaseSalary(employee.getBaseSalary());
        updateEmployee.setHireDate(employee.getHireDate());
        updateEmployee.setJobTitle(employee.getJobTitle());
        updateEmployee.setDateOfBirth(employee.getDateOfBirth());
        updateEmployee.setUpdatedDate(LocalDateTime.now());

        Employee savedEmployee = employeeRepository.save(updateEmployee);

        // Map Employee entity to EmployeeModel
        return employeeMapper.toEmployeeModel(savedEmployee);
    }

    @Override
    public void softDeleteById(String id) {
        Employee deleteEmployee = employeeRepository.findById(id).get();
        if (deleteEmployee == null || deleteEmployee.isDelete) {
            throw new RuntimeException("Employee not found");
        }
        deleteEmployee.isDelete = true;
        employeeRepository.save(deleteEmployee);
    }
}
