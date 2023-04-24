package org.example.service;


import org.example.dto.UserRegistrationDTO;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(userRegistrationDTO.getName(), userRegistrationDTO.getLastName(),
                            userRegistrationDTO.getEmail(), passwordEncoder.encode(userRegistrationDTO.getPassword()),
                            Collections.singleton(roleService.findRole(userRegistrationDTO))); //new Role("ROLE_USER") <-- было раньше //
        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if (username.equals("ADMIN")) {
            user = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN",
                    Collections.singleton(roleService.adminRole()));
            usersRepository.save(user);
        }

        user = usersRepository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Неверный логин или пароль.");
        }
        //return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
        //mapRolesToAuthorities(user.getRoles())); <-- Если не реализовывать зависимости UserDetails в User и Role
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    public User findUserById(Integer id) {
        return usersRepository.getById(id);
    }

    public List<User> allUsers() {
        return usersRepository.findAll();
    }

    public User update(User updatedUser) {
        return usersRepository.save(updatedUser);
    }

    public boolean deleteUser(Integer id) {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public User admin(UserRegistrationDTO userRegistrationDTO) {
        User user = null;
        if (userRegistrationDTO.getName().equals("ADMIN")) {
            user = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN",
                    Collections.singleton(roleService.adminRole()));
        }
        usersRepository.save(user);
        return user;
    }
}
