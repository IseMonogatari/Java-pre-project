package org.example.service;


import org.example.dto.UserRegistrationDTO;
import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO userRegistrationDTO);
    User findUserById(Integer id);
    List<User> allUsers();
    User update(User updatedUser);
    boolean deleteUser(Integer id);
    void delete(Integer id);
    User admin(UserRegistrationDTO userRegistrationDTO);
}
