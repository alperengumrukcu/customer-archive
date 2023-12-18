package com.alperen.project.service.impl;

import com.alperen.project.helper.interfaces.JwtHelper;
import com.alperen.project.model.dto.UserDTO;
import com.alperen.project.model.entity.User;
import com.alperen.project.model.exception.notfound.UserNotFoundException;
import com.alperen.project.model.request.user.LoginRequest;
import com.alperen.project.model.request.user.RegisterRequest;
import com.alperen.project.model.response.user.LoginResponse;
import com.alperen.project.repository.UserRepository;
import com.alperen.project.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtHelper jwtHelper;
    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    @Override
    public void deleteById(Long id) {

    }
    @Override
    public User dto2entity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
    @Override
    public UserDTO entity2dto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setSurname(user.getSurname());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }
    @Override
    public void register(RegisterRequest registerRequest) {
        boolean isPresent = userRepository.findByEmail(registerRequest.getEmail()).isPresent();
        if(isPresent) throw new RuntimeException("Kullanıcı zaten mevcut.");
        User user = modelMapper.map(registerRequest,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        UserDTO userDTO = entity2dto((User) authentication.getPrincipal());
        String accessToken = jwtHelper.generateToken(userDTO.getId());
        LoginResponse response = new LoginResponse();
        response.setUser(userDTO);
        response.setAccessToken(accessToken);
        return response;
    }

}