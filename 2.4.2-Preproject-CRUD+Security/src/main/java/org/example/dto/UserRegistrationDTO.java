package org.example.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String name;
    private String lastName;
    private String email;
    private String password;

    public UserRegistrationDTO() {
    }
}
