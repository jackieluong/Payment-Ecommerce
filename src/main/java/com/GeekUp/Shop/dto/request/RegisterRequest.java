package com.GeekUp.Shop.dto.request;

import com.GeekUp.Shop.annotation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @StrongPassword
    private String password;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be minimum 2 characters and maximum 20 characters")
    private String name;
}
