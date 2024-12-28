package com.ducdpg.employee_demo.models.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResgisterModel {

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email ID cannot be blank")
    @Email(message = "Email is not valid")
    public String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    public String password;

    @NotNull(message = "Full name cannot be null")
    @NotBlank(message = "Full name cannot be blank")
    public String fullName;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    public String phoneNumber;
}
