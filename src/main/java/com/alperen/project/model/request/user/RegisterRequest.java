package com.alperen.project.model.request.user;

import com.alperen.project.model.dto.UserDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RegisterRequest{
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email(message = "{validation.email}")
    @NotBlank(message = "{validation.email.blank}")
    private String email;
    @Size(min = 6)
    @NotBlank(message = "{validation.password.blank}")
    private String password;
    @Size(min = 10,max = 11)
    @NotBlank
    private String phoneNumber;
}