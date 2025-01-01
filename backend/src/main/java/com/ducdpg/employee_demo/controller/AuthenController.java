package com.ducdpg.employee_demo.controller;

import com.ducdpg.employee_demo.models.ResponseModel;
import com.ducdpg.employee_demo.models.account.AuthenRequest;
import com.ducdpg.employee_demo.models.account.AuthenResponse;
import com.ducdpg.employee_demo.models.account.ResgisterModel;
import com.ducdpg.employee_demo.models.employee.EmployeeCreateModel;
import com.ducdpg.employee_demo.models.employee.EmployeeModel;
import com.ducdpg.employee_demo.services.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthenController {

    private final IAccountService accountService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenRequest authenRequest) {
        try {
            AuthenResponse authenResponse = accountService.login(authenRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
                    200,
                    authenResponse,
                    "Login successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseModel(
                    401,
                    "",
                    e.getMessage()
            ));
        }
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody ResgisterModel resgisterModel) {
        try {
            accountService.register(resgisterModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(
                    201,
                    null,
                    "Account created successfully")
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
                    400,
                    "",
                    e.getMessage()
            ));
        }
    }
}
