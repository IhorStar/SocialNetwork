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
    AuthenticationFilter authenticationFilter;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        authenticationFilter = new AuthenticationFilter();
    }

    @Test
    public void doFilterTest() throws Exception {
        when(request.getSession(false)).thenReturn(null);
        when(request.getRequestURI()).thenReturn("");
        authenticationFilter.doFilter(request, response, filterChain);
        verify(request).getSession(false);
        verify(request).getRequestURI();
    }
}
