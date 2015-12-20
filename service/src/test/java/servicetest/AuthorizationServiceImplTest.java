package servicetest;

import dao.DAOException;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.implementation.AuthorizationServiceImpl;

public class AuthorizationServiceImplTest extends Assert {
    private AuthorizationServiceImpl authorizationService;
    User admin;
    User user;

    @Before
    public void setUp() throws DAOException {
        authorizationService = new AuthorizationServiceImpl();
        admin.setUserId(1);
        admin.setName("admin");
        admin.setPassword("qwerty");
        admin.setEmail("admin@gmail.com");
        admin.setRoleId(1);

        user.setUserId(2);
        user.setName("user");
        user.setPassword("qwerty");
        user.setEmail("user@gmail.com");
        user.setRoleId(2);
    }

    @Test
    public void testIsAdmin() throws DAOException {
        assertTrue(authorizationService.isAdmin(admin));
        assertFalse(authorizationService.isAdmin(user));
    }

    @Test
    public void testIsUser() throws DAOException {
        assertTrue(authorizationService.isUser(user));
        assertFalse(authorizationService.isUser(admin));
    }
}
