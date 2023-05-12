package org.example3.PreprojectCRUDSecurityBootstrap.repository;


import org.example3.PreprojectCRUDSecurityBootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
