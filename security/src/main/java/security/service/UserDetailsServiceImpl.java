package security.service;

import dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        entity.User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (DAOException e) {
            LOGGER.error("Database connection problem", e);
        }
        return (UserDetails) user;
    }
}
