package com.tennishub.tennishub.dtos.user;
import com.tennishub.tennishub.model.User;

public record UserCreateDto(
    String firstName,
    String lastName,
    String email,
    String password,
    String phoneNumber
) {
    public User toEntity() {
        return new User(
            firstName,
            lastName,
            email,
            password,
            phoneNumber
        );
    }
}