package com.alperen.project.model.entity;

import com.alperen.project.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customer",schema = "public")
public class Customer extends BaseEntity {
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "surname",nullable = false)
    private String surname;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "address")
    private String address;
}
