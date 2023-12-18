package com.alperen.project.model.entity;

import com.alperen.project.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Data
@Entity
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(nullable = false)
    private String authority;
}