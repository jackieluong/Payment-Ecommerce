package com.GeekUp.Shop.service.Impl;

import com.GeekUp.Shop.constant.Role;
import com.GeekUp.Shop.dto.request.RegisterRequest;
import com.GeekUp.Shop.dto.response.RegisterResponse;
import com.GeekUp.Shop.entity.User;
import com.GeekUp.Shop.exception.DuplicateException;
import com.GeekUp.Shop.repository.UserRepository;
import com.GeekUp.Shop.security.JwtUtil;
import com.GeekUp.Shop.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BaseUserService implements IUserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Autowired
    public BaseUserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String login(LoginRequest loginRequest, Account user) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
//        Authentication authentication = authenticationManager.authenticate(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwtToken = jwtUtil.createAccessToken(user);
//
//        return jwtToken;
//
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse registerUser(RegisterRequest registerRequest) throws DuplicateException {
        // add check for email exists in database
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            // throw new RuntimeException("Email is already exist");



            throw new DuplicateException("Email or nickname already exist");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        // Role userRole = roleRepository.findByName("USER").get();
        // user.getRoles().add(userRole);

        try {
            User addedUser = userRepository.save(user);

            return modelMapper.map(addedUser, RegisterResponse.class);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new RuntimeException("Error register user");
        }

    }


}
