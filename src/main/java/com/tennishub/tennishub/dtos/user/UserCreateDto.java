package com.tennishub.tennishub.dtos.user;

public record UserCreateDto(
    String firstName,
    String lastName,
    String email,
    String password,
    String phoneNumber
) {
}