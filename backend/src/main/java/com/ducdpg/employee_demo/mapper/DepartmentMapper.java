package com.ducdpg.employee_demo.mapper;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.models.department.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    // Maps a Department entity to a DepartmentModel
    DepartmentModel toDepartmentModel(Department department);

    // Maps a list of Department entities to a list of DepartmentModels
    List<DepartmentModel> toDepartmentModelList(List<Department> departments);

    // Maps a DepartmentModel to a Department entity
    @Mapping(target = "employee", ignore = true) // Ignore the association with employees
    Department toDepartmentEntity(DepartmentModel departmentModel);
}