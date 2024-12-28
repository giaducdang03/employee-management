package com.ducdpg.employee_demo.services.imp;

import com.ducdpg.employee_demo.entity.Department;
import com.ducdpg.employee_demo.mapper.DepartmentMapper;
import com.ducdpg.employee_demo.models.department.DepartmentCreateModel;
import com.ducdpg.employee_demo.models.department.DepartmentModel;
import com.ducdpg.employee_demo.models.department.DepartmentUpdateModel;
import com.ducdpg.employee_demo.repositories.DepartmentRepository;
import com.ducdpg.employee_demo.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentModel> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentMapper.toDepartmentModelList(departmentList);
    }

    @Override
    public DepartmentModel getById(String id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            return null;
        }
        return departmentMapper.toDepartmentModel(department.get());
    }

    @Override
    public DepartmentModel save(DepartmentCreateModel department) {
        // check name exist department
        Department existedDepartment = departmentRepository.findByName(department.getName());
        if (existedDepartment != null) {
            throw new RuntimeException("Department name already exist");
        }

        Department newDepartment = new Department();
        newDepartment.setName(department.getName());
        newDepartment.setDescription(department.getDescription());
        newDepartment.setCreatedDate(LocalDateTime.now());
        newDepartment.isDelete = false;

        Department savedDepartment = departmentRepository.save(newDepartment);

        return departmentMapper.toDepartmentModel(savedDepartment);
    }

    @Override
    public DepartmentModel update(DepartmentUpdateModel department) {
        // check update department
        Department existedDepartment = departmentRepository.findByName(department.getId());
        if (existedDepartment == null) {
            throw new RuntimeException("Department not exist");
        }

        // check name exist department
        Department existedNameDepartment = departmentRepository.findByName(department.getName());
        if (existedNameDepartment != null) {
            throw new RuntimeException("Department name already exist");
        }

        existedDepartment.setName(department.getName());
        existedDepartment.setDescription(department.getDescription());
        existedDepartment.setUpdatedDate(LocalDateTime.now());

        Department savedDepartment = departmentRepository.save(existedDepartment);

        return departmentMapper.toDepartmentModel(savedDepartment);
    }
}
