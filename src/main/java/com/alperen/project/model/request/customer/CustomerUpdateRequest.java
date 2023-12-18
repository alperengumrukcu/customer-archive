package com.alperen.project.model.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerUpdateRequest {
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email(message = "{validation.email}")
    @NotBlank(message = "{validation.email.blank}")
    private String email;
    @NotBlank
    private String address;
}
