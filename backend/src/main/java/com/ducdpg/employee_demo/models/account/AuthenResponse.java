package com.ducdpg.employee_demo.models.account;

import lombok.*;

@Data
@Getter
@Setter
@Builder
public class AuthenResponse {
    public String accessToken;
    public String refreshToken;
}
