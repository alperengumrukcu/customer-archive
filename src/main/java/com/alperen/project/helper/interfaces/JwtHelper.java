package com.alperen.project.helper.interfaces;

public interface JwtHelper {
    String generateToken(Long userId);
    Long getUserIDinToken(String token);
}