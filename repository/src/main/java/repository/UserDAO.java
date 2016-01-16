package repository;

import entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int userId);
    User getUserBy(String email, String password);
    User getUserByEmail(String email);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getAllUsers();
}
