import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import service.implementation.AuthorizationServiceImpl;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class AuthorizationTest {
    private AuthorizationServiceImpl authorizationService;
    User user = new User();
    private int idRole;
    private boolean expected;

    @Before
    public void setUp() {
        authorizationService = new AuthorizationServiceImpl();
        user.setRoleId(idRole);
    }

    public AuthorizationTest(int idRole, boolean expected) {
        this.idRole = idRole;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1, true},
                {2, false}
        });
    }

    @Test
    public void testIsAdmin() {
        assertEquals(expected, authorizationService.isAdmin(user));
    }
}
