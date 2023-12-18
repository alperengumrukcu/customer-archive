package com.alperen.project.controller.impl;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.controller.interfaces.AuthController;
import com.alperen.project.model.request.user.LoginRequest;
import com.alperen.project.model.request.user.RegisterRequest;
import com.alperen.project.model.response.user.LoginResponse;
import com.alperen.project.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {
    private UserService userService;
    @Override
    public ResponseEntity<BaseResponse<?>> createUser(RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return BaseResponse.ok(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<BaseResponse<LoginResponse>> login(LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        return BaseResponse.ok(response);
    }
}