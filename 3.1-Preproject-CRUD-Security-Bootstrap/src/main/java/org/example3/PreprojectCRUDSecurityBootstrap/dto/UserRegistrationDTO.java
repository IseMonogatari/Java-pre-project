package org.example3.PreprojectCRUDSecurityBootstrap.dto;

import lombok.Data;
import org.example3.PreprojectCRUDSecurityBootstrap.model.Role;

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
}
