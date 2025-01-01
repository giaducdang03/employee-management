package com.ducdpg.employee_demo.services;

import com.ducdpg.employee_demo.models.ResponseModel;
import com.ducdpg.employee_demo.models.account.AuthenRequest;
import com.ducdpg.employee_demo.models.account.AuthenResponse;
import com.ducdpg.employee_demo.models.account.ResgisterModel;

public interface IAccountService {
    void register(ResgisterModel resgisterModel);
    AuthenResponse login(AuthenRequest authenRequest);
}
