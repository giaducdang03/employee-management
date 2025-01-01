package com.ducdpg.employee_demo.models.account;

import lombok.*;

@Data
@Setter
@Getter
public class AuthenRequest {
    public String email;
    public String password;
}
