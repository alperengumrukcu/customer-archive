package com.alperen.project.model.dto;

import lombok.Data;


@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
