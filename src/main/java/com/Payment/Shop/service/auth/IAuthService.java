package com.Payment.Shop.service.auth;


import com.Payment.Shop.dto.request.LoginRequest;
import com.Payment.Shop.dto.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
}
