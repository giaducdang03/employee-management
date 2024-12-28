package com.ducdpg.employee_demo.models.department;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentUpdateModel extends DepartmentCreateModel {
    public String id;
}
