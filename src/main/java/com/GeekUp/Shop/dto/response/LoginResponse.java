package com.GeekUp.Shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private Long expiresIn;
}
