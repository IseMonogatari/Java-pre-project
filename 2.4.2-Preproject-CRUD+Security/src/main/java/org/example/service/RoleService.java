package org.example.service;

import org.example.dto.RoleDTO;
import org.example.dto.UserRegistrationDTO;
import org.example.model.Role;

public interface RoleService {
    Role save(RoleDTO roleDTO);

    Role findRole(UserRegistrationDTO userRegistrationDTO);

    Role adminRole();
}
