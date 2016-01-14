package service.implementation;

import dao.DAOException;
import dao.UserDAO;
import entity.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserDAO dao;

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) throws DAOException {
        dao.addUser(user);
    }

    public User getUserById(int userId) throws DAOException {
        return dao.getUserById(userId);
    }

    public User getUserBy(String email, String password) throws DAOException {
        return dao.getUserBy(email, password);
    }

    public User getUserByEmail(String email) throws DAOException{
        return dao.getUserByEmail(email);
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
        LOGGER.info("Bean is going through init.");
    }

    public void destroy() {
        LOGGER.info("Bean will destroy now.");
    }
}
