package com.tennishub.tennishub.service;

import com.tennishub.tennishub.dtos.user.UserCreateDto;
import com.tennishub.tennishub.dtos.user.UserResponseDto;
import com.tennishub.tennishub.model.User;
import com.tennishub.tennishub.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldEncodePasswordAndSaveUser() {
        // Arrange
        UserCreateDto dto = new UserCreateDto("John", "Doe", "john@test.com", "plainPassword", "0600000000");
        User savedUser = new User("John", "Doe", "john@test.com", "hashedPassword", "0600000000");
        savedUser.setId(1);

        when(passwordEncoder.encode("plainPassword")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserResponseDto result = userService.createUser(dto);

        // Assert
        assertThat(result.id()).isEqualTo(1);
        assertThat(result.email()).isEqualTo("john@test.com");
        verify(passwordEncoder).encode("plainPassword");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getUser_shouldReturnUser_whenUserExists() {
        // Arrange
        User user = new User("John", "Doe", "john@test.com", "hashedPassword", "0600000000");
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Act
        UserResponseDto result = userService.getUser(1);

        // Assert
        assertThat(result.id()).isEqualTo(1);
        assertThat(result.firstName()).isEqualTo("John");
    }

    @Test
    void getUser_shouldThrowException_whenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> userService.getUser(99));
    }
}