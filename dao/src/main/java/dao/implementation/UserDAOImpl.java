package dao.implementation;

import dao.DAOException;
import dao.UserDAO;
import dao.mapper.UserMapper;
import entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addUser(User user) throws DAOException {
        String query = "insert into user(user_id, name, password, email, role_id) values (?, ?, ?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{user.getUserId(), user.getName(), user.getPassword(), user.getEmail(),
                user.getRoleId()});
    }

    public User getUserById(int id) throws DAOException {
        String query = "select * from user where user_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        User user = (User) jdbcTemplate.queryForObject(query, new Object[]{id}, new UserMapper());
        return user;

    }

    public User getUserBy(String email, String password) throws DAOException {
        String query = "select * from user where email = ? and password = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        User user = (User) jdbcTemplate.queryForObject(query, new Object[]{email, password}, new UserMapper());
        return user;
    }

    public User getUserByEmail(String email) throws DAOException {
        String query = "select * from user where email = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        User user = (User)jdbcTemplate.queryForObject(query, new Object[]{email}, new UserMapper());
        return user;
    }
    public void updateUser(User user) throws DAOException {
        String query = "update user set user_id = ?, name = ?, password = ?, email = ? where user_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{user.getUserId(), user.getName(), user.getPassword(), user.getEmail(),
                user.getRoleId()});
    }

    public void deleteUserById(int userId) throws DAOException {
        String query = "delete from user where user_id = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(query, new Object[]{userId});
    }

    public List<User> getAllUsers() throws DAOException {
        String query = "select id, name from user;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        List users = jdbcTemplate.query(query, new UserMapper());
        return users;
    }
}
