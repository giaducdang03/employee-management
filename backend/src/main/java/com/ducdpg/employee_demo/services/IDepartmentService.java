package com.ducdpg.employee_demo.services;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.models.department.DepartmentCreateModel;
import com.ducdpg.employee_demo.models.department.DepartmentModel;
import com.ducdpg.employee_demo.models.department.DepartmentUpdateModel;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<DepartmentModel> getAll();
    DepartmentModel getById(String id);
    DepartmentModel save(DepartmentCreateModel department);
    DepartmentModel update(DepartmentUpdateModel department);

}
