package service.implementation;


import dao.DAOException;
import dao.UserDAO;
import dao.implementation.UserDAOImpl;
import entity.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO dao = new UserDAOImpl();

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public void addUser(User user) throws DAOException, SQLException {
        dao.addUser(user);
    }

    public User getUserById(int userId) throws DAOException, SQLException {
        return dao.getUserById(userId);
    }

    public User getUserBy(String email, String password) throws DAOException, SQLException {
        return dao.getUserBy(email, password);
    }

    public void updateUser(User user) throws DAOException, SQLException {
        dao.updateUser(user);
    }

    public void deleteUserById(int userId) throws DAOException, SQLException {
        dao.deleteUserById(userId);
    }

    public List<User> getAllUsers() throws DAOException, SQLException {
        return dao.getAllUsers();
    }

}
