package com.GeekUp.Shop.service;


import com.GeekUp.Shop.dto.request.LoginRequest;
import com.GeekUp.Shop.dto.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
}
