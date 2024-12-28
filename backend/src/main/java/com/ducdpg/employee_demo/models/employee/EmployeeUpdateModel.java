package com.ducdpg.employee_demo.models.employee;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeUpdateModel extends EmployeeCreateModel{
    public String id;
}
