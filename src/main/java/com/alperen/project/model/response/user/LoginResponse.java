package com.alperen.project.model.response.user;

import com.alperen.project.model.dto.UserDTO;
import lombok.Data;

@Data
public class LoginResponse {
    private UserDTO user;
    private String accessToken;
}
