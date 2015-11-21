import org.junit.Before;
import org.junit.Test;
import servlets.AuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class AuthentificationFilterTest {
    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain filterChain;
    AuthenticationFilter filter = new AuthenticationFilter();

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
    }

    @Test
    public void doFilterTest() throws Exception {
        filter.doFilter(request, response, filterChain);
        verify(response).sendRedirect("/login.html");
    }
}
