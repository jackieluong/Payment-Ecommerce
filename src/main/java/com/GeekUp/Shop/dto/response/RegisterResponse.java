package com.GeekUp.Shop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private long userId;

    private String email;

    private String name;
}
