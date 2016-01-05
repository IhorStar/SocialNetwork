package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user) throws DAOException;
    User getUserById(int userId) throws DAOException;
    User getUserBy(String email, String password) throws DAOException;
    User getUserByEmail(String email) throws DAOException;
    void updateUser(User user) throws DAOException;
    void deleteUserById(int userId) throws DAOException;
    List<User> getAllUsers() throws DAOException;
}
