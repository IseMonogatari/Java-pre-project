package org.example3.PreprojectCRUDSecurityBootstrap.repository;


import org.example3.PreprojectCRUDSecurityBootstrap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findUserById(Integer id);
}
