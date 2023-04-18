package org.example.service;

import org.example.model.User;
import org.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User create(User user) {
        return usersRepository.save(new User(user.getName(), user.getLastName(), user.getSex(), user.getAge()));
    }

    public List<User> readAll() {
        return usersRepository.findAll();
    }

    public User getUserById(Integer id) {
        return usersRepository.getReferenceById(id);
    }

    public User update(User updatedUser) {
        return usersRepository.save(updatedUser);
    }

    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }
}
