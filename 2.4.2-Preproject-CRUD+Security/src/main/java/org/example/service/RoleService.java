package org.example.service;

import org.example.dto.RoleDTO;
import org.example.dto.UserRegistrationDTO;
import org.example.model.Role;
import org.springframework.beans.factory.annotation.Qualifier;

public interface RoleService {
    Role save(RoleDTO roleDTO);
    Role userRole();
}
