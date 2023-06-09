package org.preproject.CRUDSecAndFront.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String name, String lastName, String email, String password, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String toStringWithoutPassAndRole() {
        return id + " " + lastName + " " + name + " " + email;
    }
}
