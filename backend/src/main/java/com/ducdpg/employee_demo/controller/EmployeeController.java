package com.ducdpg.employee_demo.controller;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.entity.Employee;
import com.ducdpg.employee_demo.models.ResponseModel;
import com.ducdpg.employee_demo.models.employee.EmployeeCreateModel;
import com.ducdpg.employee_demo.models.employee.EmployeeModel;
import com.ducdpg.employee_demo.models.employee.EmployeeUpdateModel;
import com.ducdpg.employee_demo.services.IDepartmentService;
import com.ducdpg.employee_demo.services.IEmployeeService;
import com.ducdpg.employee_demo.services.imp.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(required = false) String departmentId,
                                             @RequestParam(required = false) String fullName,
                                             @RequestParam(required = false) String[] sort) {
        try {
            Page<EmployeeModel> employeeList = employeeService.getAll(page, size, departmentId, fullName, sort);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    employeeList,
                    "Employee retrieved successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
                    400,
                    null,
                    e.getMessage()
            ));
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeCreateModel employeeModel) {
        try {
            EmployeeModel result = employeeService.save(employeeModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(
                    201,
                    result,
                    "Employee created successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
                    400,
                    "",
                    e.getMessage()
            ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        try {
            EmployeeModel employee = employeeService.getById(id);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
                        404,
                        null,
                        "Employee not found"
                ));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    employee,
                    "Employee retrieved successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
                    400,
                    "",
                    e.getMessage()
            ));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeUpdateModel employeeModel) {
        try {
            EmployeeModel result = employeeService.update(employeeModel);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    result,
                    "Employee updated successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
                    400,
                    null,
                    e.getMessage()
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        try {
            employeeService.softDeleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    null,
                    "Employee deleted successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
                    400,
                    null,
                    e.getMessage()
            ));
        }
    }
}
