package com.ducdpg.employee_demo.controller;

import com.ducdpg.employee_demo.models.ResponseModel;
import com.ducdpg.employee_demo.models.department.DepartmentCreateModel;
import com.ducdpg.employee_demo.models.department.DepartmentModel;
import com.ducdpg.employee_demo.models.department.DepartmentUpdateModel;
import com.ducdpg.employee_demo.services.IDepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Validated
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAllDepartments(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        try {
            Page<DepartmentModel> listDepartment = departmentService.getAll(page, size);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    listDepartment,
                    "Departments retrieved successfully")
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
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        try {
            DepartmentModel departmentModel = departmentService.getById(id);
            if (departmentModel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
                        404,
                        null,
                        "Department not found"
                ));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    departmentModel,
                    "Department retrieved successfully")
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
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentCreateModel departmentModel) {
        try {
            DepartmentModel result = departmentService.save(departmentModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(
                    201,
                    result,
                    "Department created successfully")
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

    @PutMapping
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody DepartmentUpdateModel departmentModel) {
        try {
            DepartmentModel result = departmentService.update(departmentModel);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    result,
                    "Department created successfully")
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
