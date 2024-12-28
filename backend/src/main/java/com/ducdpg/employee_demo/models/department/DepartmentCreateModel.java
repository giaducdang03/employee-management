package com.ducdpg.employee_demo.models.department;

import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentCreateModel {

    public String name;

    public String description;
}
