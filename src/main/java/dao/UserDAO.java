package dao;

import entity.User;

public interface UserDAO {
    void addUser(User user) throws DAOException;
    User getUserById(int userId) throws DAOException;
    User getUserBy(String email, String password) throws DAOException;
    void updateUser(User user) throws DAOException;
    void deleteUser(User user) throws DAOException;


}
