package service.implementation;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserDAO;
import service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public void addUser(User user) {
        dao.addUser(user);
    }

    public User getUserById(int userId) {
        return dao.getUserById(userId);
    }

    public User getUserBy(String email, String password) {
        return dao.getUserBy(email, password);
    }

    public User getUserByEmail(String email) {
        return dao.getUserByEmail(email);
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    public void deleteUserById(User user) {
        dao.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }
}
