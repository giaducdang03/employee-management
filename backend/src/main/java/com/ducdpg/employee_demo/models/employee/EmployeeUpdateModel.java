package com.ducdpg.employee_demo.models.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeUpdateModel extends EmployeeCreateModel{

    @NotNull(message = "Employee id cannot be null")
    @NotBlank(message = "Employee id cannot be blank")
    public String id;
}
