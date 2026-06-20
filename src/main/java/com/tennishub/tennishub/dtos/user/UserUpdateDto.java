package com.tennishub.tennishub.dtos.user;
import com.tennishub.tennishub.model.User;

public record UserUpdateDto (
    String firstName,
    String lastName,
    String email,
    String phoneNumber
)   {
        public void applyTo(User user) {
            if (firstName != null)      user.setFirstName(firstName);
            if (lastName != null)       user.setLastName(lastName);
            if (email != null)          user.setEmail(email);
            if (phoneNumber != null)    user.setPhoneNumber(phoneNumber);
        }
    }
