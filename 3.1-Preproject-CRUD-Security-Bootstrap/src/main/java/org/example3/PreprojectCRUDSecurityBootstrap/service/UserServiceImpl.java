package org.example3.PreprojectCRUDSecurityBootstrap.service;


import org.example3.PreprojectCRUDSecurityBootstrap.dto.UserRegistrationDTO;
import org.example3.PreprojectCRUDSecurityBootstrap.model.User;
import org.example3.PreprojectCRUDSecurityBootstrap.repository.RolesRepository;
import org.example3.PreprojectCRUDSecurityBootstrap.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public User save(UserRegistrationDTO userDTO) {
        User user = usersRepository.findByName(userDTO.getName());
        if (user != null && user.getRoles().contains(rolesRepository.findByRole("ROLE_ADMIN"))) {
            user.getRoles().add(roleService.getRoleByName(userDTO.getRole()));
        } else if (user != null && user.getRoles().contains(rolesRepository.findByRole("ROLE_USER"))) {
            return null;
        } else {
            user = new User();
            user.setLastName(userDTO.getLastName());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRoles(Collections.singleton(roleService.getRoleByName(userDTO.getRole()))); //new Role(userRegistrationDTO.getRoles())
        }

        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Неверный логин или пароль.");
        }
        return user;
    }

    public User findUserById(Integer id) {
        //TODO родной метод getById(id) делает ленивую загрузку,
        // поэтому и выдавало ошибку выгрузки пользователя.
        // Нужно реализовать свой собственный метод получения пользоватебя из БД -- findUserById(id).
        return usersRepository.findUserById(id);
    }

    public List<User> allUsers() {
        return usersRepository.findAll();
    }

    public User update(UserRegistrationDTO userDTO) {
        User updatedUser = usersRepository.findUserById(Integer.valueOf(userDTO.getId()));

        if (updatedUser.getRoles().contains(rolesRepository.findByRole("ROLE_ADMIN"))
                && !Objects.equals(userDTO.getRole(), "ROLE_ADMIN")) {
            updatedUser.setLastName(userDTO.getLastName());
            updatedUser.setName(userDTO.getName());
            updatedUser.setEmail(userDTO.getEmail());
            updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            updatedUser.getRoles().add(rolesRepository.findByRole(userDTO.getRole()));

        } else if (updatedUser.getRoles().contains(rolesRepository.findByRole("ROLE_ADMIN"))
                && Objects.equals(userDTO.getRole(), "ROLE_ADMIN")) {
            updatedUser.setLastName(userDTO.getLastName());
            updatedUser.setName(userDTO.getName());
            updatedUser.setEmail(userDTO.getEmail());
            updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        } else if (updatedUser.getRoles().contains(rolesRepository.findByRole("ROLE_USER"))
                && Objects.equals(userDTO.getRole(), "ROLE_ADMIN")) {
            return null;

        } else {
            updatedUser.setLastName(userDTO.getLastName());
            updatedUser.setName(userDTO.getName());
            updatedUser.setEmail(userDTO.getEmail());
            updatedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        return usersRepository.save(updatedUser);
    }

    public boolean deleteUser(Integer id) {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
