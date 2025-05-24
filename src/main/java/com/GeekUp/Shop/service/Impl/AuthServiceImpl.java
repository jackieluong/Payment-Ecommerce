package com.GeekUp.Shop.service.Impl;


import com.GeekUp.Shop.dto.request.LoginRequest;
import com.GeekUp.Shop.dto.response.LoginResponse;
import com.GeekUp.Shop.entity.Account;
import com.GeekUp.Shop.exception.ResourceNotFoundException;
import com.GeekUp.Shop.security.JwtUtil;
import com.GeekUp.Shop.service.IAccountService;
import com.GeekUp.Shop.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IAccountService accountService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil,  IAccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.accountService = accountService;

    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {


        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        try{
            Account user = accountService.getAccountByEmail(loginRequest.getUsernameOrEmail());
            String jwtToken = jwtUtil.createAccessToken(user);

            return new LoginResponse(jwtToken, "", "Bearer", jwtUtil.getAccessTokenExpiration() );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadCredentialsException("Email or password is incorrect");
        }



        }

}
