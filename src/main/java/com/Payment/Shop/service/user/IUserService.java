package com.Payment.Shop.service.user;


import com.Payment.Shop.dto.request.RegisterRequest;
import com.Payment.Shop.dto.response.RegisterResponse;
import com.Payment.Shop.exception.DuplicateException;

public interface IUserService {
//    String login(LoginRequest loginRequest, User user);

    RegisterResponse registerUser(RegisterRequest registerRequest) throws DuplicateException;


}
