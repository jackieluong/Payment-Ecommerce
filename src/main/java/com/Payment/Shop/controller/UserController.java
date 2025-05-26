package com.Payment.Shop.controller;


import com.Payment.Shop.dto.ResultObject;
import com.Payment.Shop.dto.request.RegisterRequest;
import com.Payment.Shop.dto.response.RegisterResponse;
import com.Payment.Shop.exception.DuplicateException;
import com.Payment.Shop.security.JwtUtil;
import com.Payment.Shop.service.user.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;
    private final JwtUtil jwtUtil;



    @PostMapping("/signup")
    public ResponseEntity<ResultObject> register(@Valid @RequestBody RegisterRequest registerRequest) throws JsonProcessingException, DuplicateException {
        StringBuilder sb = new StringBuilder();
            // Tạo ObjectMapper để chuyển RegisterDto thành chuỗi JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String registerDtoJson = objectMapper.writeValueAsString(registerRequest);

            // Thêm vào StringBuilder
            sb.append(String.format("New user register at %s: \n", LocalDateTime.now()))
                    .append(registerDtoJson)
                    .append("\n");

            // System.out.println("RegisterDto: " + registerDto);
            RegisterResponse response = userService.registerUser(registerRequest);


            ResultObject responseDto = ResultObject.builder()
                    .isSuccess(true)
                    .httpStatus(HttpStatus.CREATED)
                    .message("Register successfully")
                    .data(response)
                    .build();

            sb.append("\r\tRegister successfully");

            log.info(sb.toString());

            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        }



}



