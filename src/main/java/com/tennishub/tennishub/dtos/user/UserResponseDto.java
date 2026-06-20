package com.tennishub.tennishub.dtos.user;

public record UserResponseDto(
    Integer Id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber
)   {
        public UserResponseDto fromEntity() {
            return new UserResponseDto(
                Id,
                firstName,
                lastName,
                email,
                phoneNumber);
        }
    }
