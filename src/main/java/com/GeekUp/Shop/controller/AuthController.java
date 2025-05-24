package com.GeekUp.Shop.controller;


import com.GeekUp.Shop.dto.ResultObject;
import com.GeekUp.Shop.dto.request.LoginRequest;
import com.GeekUp.Shop.dto.response.LoginResponse;
import com.GeekUp.Shop.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private final IAuthService authService;


    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResultObject> login(@Valid @RequestBody LoginRequest loginRequest) {


        LoginResponse loginResponse = authService.login(loginRequest);
        ResultObject responseDto = ResultObject.builder()
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .message("Login successfully")
                .data(loginResponse)
                .build();



        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
