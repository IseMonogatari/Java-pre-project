package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    List<User> getWithAuto(String model, int series);
    long getCarID(String model, int series);
}
