package com.ducdpg.employee_demo.models.department;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentCreateModel {

    @NotNull(message = "Department name cannot be null")
    @NotBlank(message = "Department name cannot be blank")
    public String name;

    public String description;
}
