package com.alperen.project.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private LocalDateTime createdAt;
}
