package com.alperen.project.model.entity;

import com.alperen.project.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity
@Table(name = "users",schema = "public")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "surname",nullable = false)
    private String surname;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isAccountNonExpired = true;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isAccountNonLocked = true;
    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isCredentialsNonExpired = true;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isEnabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> authorities;
    @Override
    public String getUsername() {
        return this.email;
    }
}