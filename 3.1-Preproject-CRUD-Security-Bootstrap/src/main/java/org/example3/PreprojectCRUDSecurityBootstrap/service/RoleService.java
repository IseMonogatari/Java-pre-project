package org.example3.PreprojectCRUDSecurityBootstrap.service;


import org.example3.PreprojectCRUDSecurityBootstrap.dto.RoleDTO;
import org.example3.PreprojectCRUDSecurityBootstrap.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RoleService {
    Role save(RoleDTO roleDTO);
    Role userRole();

    Role getRoleByName(String role);
}
