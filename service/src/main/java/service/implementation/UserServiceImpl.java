package service.implementation;

import dao.DAOException;
import dao.UserDAO;
import entity.User;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO dao;

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public void addUser(User user) throws DAOException {
        dao.addUser(user);
    }

    public User getUserById(int userId) throws DAOException {
        return dao.getUserById(userId);
    }

    public User getUserBy(String email, String password) throws DAOException {
        return dao.getUserBy(email, password);
    }

    public void updateUser(User user) throws DAOException {
        dao.updateUser(user);
    }

    public void deleteUserById(int userId) throws DAOException {
        dao.deleteUserById(userId);
    }

    public List<User> getAllUsers() throws DAOException {
        return dao.getAllUsers();
    }

    public void init() {

    }

    public void destroy() {

    }
}
