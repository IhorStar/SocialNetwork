package servicetest;

import dao.DAOException;
import dao.implementation.UserDAOImpl;
import entity.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.implementation.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {
    private UserServiceImpl userService;
    private UserDAOImpl userDAO;

    User user1 = new User();
    User user2 = new User();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws DAOException {
        userDAO = mock(UserDAOImpl.class);

        userService = new UserServiceImpl();
        userService.setDao(userDAO);

        user1.setUserId(1);
        user1.setName("user1");
        user1.setPassword("qwerty");
        user1.setEmail("user1@gmail.com");
        user1.setRoleId(2);

        user2.setUserId(2);
        user2.setName("user2");
        user2.setPassword("qwerty");
        user2.setEmail("user2@gmail.com");
        user2.setRoleId(2);
    }

    @Test
    public void testAddUser() throws DAOException {
        userService.addUser(user1);
        verify(userDAO).addUser(user1);
    }

    @Test
    public void testGetUserById() throws DAOException {
        when(userDAO.getUserById(1)).thenReturn(user1);
        User user = userService.getUserById(1);
        verify(userDAO).getUserById(1);
        assertEquals("user1", user.getName());
    }

    @Test
    public void testGetUserById2() throws DAOException {
        doThrow(new DAOException("cannot find user with id = 0")).when(userDAO).getUserById(0);
        exception.expect(DAOException.class);
        exception.expectMessage("cannot find user with id = 0");
        User user = userDAO.getUserById(0);
    }

    @Test
    public void testGetUserBy() throws DAOException {
        when(userDAO.getUserBy("user1@gmail.com", "qwerty")).thenReturn(user1);
        User user = userService.getUserBy("user1@gmail.com", "qwerty");
        verify(userDAO).getUserBy("user1@gmail.com", "qwerty");
        assertEquals("user1", user.getName());
    }

    @Test
    public void testUpdateUser() throws DAOException {
        userService.updateUser(user1);
        verify(userDAO).updateUser(user1);
    }

    @Test
    public void testDeleteUserById() throws DAOException {
        userService.deleteUserById(1);
        verify(userDAO).deleteUserById(1);
    }

    @Test
    public void testGetAllUsers() throws DAOException {
        List<User> allUsers = new ArrayList<User>();
        int counter = 0;
        allUsers.add(user1);
        allUsers.add(user2);

        when(userDAO.getAllUsers()).thenReturn(allUsers);
        List<User> result = userService.getAllUsers();
        verify(userDAO).getAllUsers();

        for(User user : result) {
            counter++;
        }
        assertEquals(2, counter);
    }
}
