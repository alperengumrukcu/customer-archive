package com.alperen.project.model.security;

import com.alperen.project.helper.interfaces.JwtHelper;
import com.alperen.project.model.entity.User;
import com.alperen.project.service.interfaces.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtHelper jwtHelper;
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            Assert.notNull(token,"Token boş olamaz.");
            Assert.isTrue(token.startsWith("Bearer "),"Bearer token göndermelisiniz.");
            token = token.substring(7);
            Long userId = jwtHelper.getUserIDinToken(token);
            User user = userService.getById(userId);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities()));
            filterChain.doFilter(request,response);
        }catch (Exception e){
            filterChain.doFilter(request,response);
        }
    }
}