import org.junit.Before;
import org.junit.Test;
import servlets.AuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class AuthentificationFilterTest {
    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain filterChain;
    HttpSession session;
    AuthenticationFilter authenticationFilter;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        session = mock(HttpSession.class);
        authenticationFilter = new AuthenticationFilter();
    }

    @Test
    public void testDoFilter() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        when(request.getRequestURI()).thenReturn("login");
        authenticationFilter.doFilter(request, response, filterChain);
        verify(response).sendRedirect("/login.html");
    }

    @Test
    public void testDoFilter2() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        when(request.getRequestURI()).thenReturn("register");
        authenticationFilter.doFilter(request, response, filterChain);
        verify(response).sendRedirect("/login.html");
    }

    @Test
    public void testDoFilter3() throws Exception {
        when(request.getSession(true)).thenReturn(session);
        when(request.getRequestURI()).thenReturn("html");
        authenticationFilter.doFilter(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
    }
}
