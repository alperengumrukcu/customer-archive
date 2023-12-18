package com.alperen.project.controller.interfaces;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.model.request.user.LoginRequest;
import com.alperen.project.model.request.user.RegisterRequest;
import com.alperen.project.model.response.user.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {
    @PostMapping("/register")
    ResponseEntity<BaseResponse<?>> createUser(@Valid @RequestBody RegisterRequest registerRequest);
    @PostMapping("/login")
    ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest);
}