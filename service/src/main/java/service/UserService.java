package service;


import dao.DAOException;
import entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void addUser(User user) throws DAOException, SQLException;
    User getUserById(int userId) throws DAOException, SQLException;
    User getUserBy(String email, String password) throws DAOException, SQLException;
    void updateUser(User user) throws DAOException, SQLException;
    void deleteUserById(int userId) throws DAOException, SQLException;
    List<User> getAllUsers() throws DAOException, SQLException;
}
