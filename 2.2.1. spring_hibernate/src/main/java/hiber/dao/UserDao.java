package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   List<User> getWithAuto(String model, int series);
   long getCarID(String model, int series);
}
