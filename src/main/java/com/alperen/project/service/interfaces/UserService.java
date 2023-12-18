package com.alperen.project.service.interfaces;

import com.alperen.project.base.BaseService;
import com.alperen.project.model.dto.UserDTO;
import com.alperen.project.model.entity.User;
import com.alperen.project.model.request.user.LoginRequest;
import com.alperen.project.model.request.user.RegisterRequest;
import com.alperen.project.model.response.user.LoginResponse;

public interface UserService extends BaseService<User, UserDTO> {
    void register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);

}