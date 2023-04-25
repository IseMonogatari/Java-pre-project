package org.example.service;

import org.example.dto.RoleDTO;
import org.example.dto.UserRegistrationDTO;
import org.example.model.Role;
import org.example.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolesRepository roleRepository;
    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");

    @Override
    public Role save(RoleDTO roleDTO) {
        return roleRepository.save(new Role("ROLE_" + roleDTO.getRole()));
    }

    @Override
    public Role userRole() {
        return userRole;
    }

}
