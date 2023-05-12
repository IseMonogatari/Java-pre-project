package org.example3.PreprojectCRUDSecurityBootstrap.service;



import org.example3.PreprojectCRUDSecurityBootstrap.dto.UserRegistrationDTO;
import org.example3.PreprojectCRUDSecurityBootstrap.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO userRegistrationDTO);
    User findUserById(Integer id);
    List<User> allUsers();
    User update(UserRegistrationDTO userRegistrationDTO);
    boolean deleteUser(Integer id);
}
