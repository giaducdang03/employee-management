package com.ducdpg.employee_demo.mapper;

import com.ducdpg.employee_demo.entity.Employee;
import com.ducdpg.employee_demo.models.employee.EmployeeCreateModel;
import com.ducdpg.employee_demo.models.employee.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.name", target = "departmentName") // Maps department's name
    @Mapping(source = "department.id", target = "departmentId")
    EmployeeModel toEmployeeModel(Employee employee);

    @Mapping(source = "departmentName", target = "department.name")
    Employee toEmployeeEntity(EmployeeModel employeeModel);

    List<EmployeeModel> toEmployeeModelList(List<Employee> employees);

}