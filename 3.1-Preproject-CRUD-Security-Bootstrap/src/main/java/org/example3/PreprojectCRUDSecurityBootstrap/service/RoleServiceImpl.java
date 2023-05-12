package org.example3.PreprojectCRUDSecurityBootstrap.service;


import org.example3.PreprojectCRUDSecurityBootstrap.dto.RoleDTO;
import org.example3.PreprojectCRUDSecurityBootstrap.model.Role;
import org.example3.PreprojectCRUDSecurityBootstrap.repository.RolesRepository;
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

    @Override
    public Role getRoleByName(String role) {
        return roleRepository.findByRole(role);
    }


}
