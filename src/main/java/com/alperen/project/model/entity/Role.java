package com.alperen.project.model.entity;

import com.alperen.project.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Data
@Entity
@Table(name = "role",schema = "public")
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(nullable = false)
    private String authority;
}