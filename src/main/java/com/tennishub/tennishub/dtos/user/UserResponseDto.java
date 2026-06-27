package com.tennishub.tennishub.dtos.user;
import com.tennishub.tennishub.model.User;

public record UserResponseDto(
    Integer id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber
)   {
        public static UserResponseDto fromEntity(User user) {
            return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber()
            );
        }
    }
