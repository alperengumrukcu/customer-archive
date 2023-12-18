package com.alperen.project.model.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class LoginRequest {
    @Email(message = "{validation.email}")
    @NotBlank(message = "{validation.email.blank}")
    private String email;
    @NotBlank(message = "{validation.password.blank}")
    private String password;
}