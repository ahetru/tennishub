package com.tennishub.tennishub.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tennishub.tennishub.dtos.user.UserCreateDto;
import com.tennishub.tennishub.dtos.user.UserResponseDto;
import com.tennishub.tennishub.dtos.user.UserUpdateDto;
import com.tennishub.tennishub.model.User;
import com.tennishub.tennishub.repository.UserRepository;


@Service

public class UserService {
    private final UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(UserCreateDto dto) {
        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(dto.password()));
        User saved = userRepository.save(user);

        return UserResponseDto.fromEntity(saved);
    }

    public UserResponseDto updateUser(Integer id, UserUpdateDto dto) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
        dto.applyTo(user);
        User saved = userRepository.save(user);
        
        return UserResponseDto.fromEntity(saved);
    }

    public UserResponseDto getUser(Integer id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponseDto.fromEntity(user);
    }
}