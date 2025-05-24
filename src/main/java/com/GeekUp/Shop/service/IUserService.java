package com.GeekUp.Shop.service;


import com.GeekUp.Shop.dto.request.RegisterRequest;
import com.GeekUp.Shop.dto.response.RegisterResponse;
import com.GeekUp.Shop.exception.DuplicateException;

public interface IUserService {
//    String login(LoginRequest loginRequest, User user);

    RegisterResponse registerUser(RegisterRequest registerRequest) throws DuplicateException;


}
