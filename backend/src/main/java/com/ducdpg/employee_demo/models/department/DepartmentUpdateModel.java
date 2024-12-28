package com.ducdpg.employee_demo.models.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentUpdateModel extends DepartmentCreateModel {

    @NotNull(message = "Department id cannot be null")
    @NotBlank(message = "Department id cannot be blank")
    public String id;
}
