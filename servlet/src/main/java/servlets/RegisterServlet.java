package servlets;

import dao.DAOException;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegisterServlet.class);
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = null;

        if(name == null || "".equals(name)) {
            errorMessage = messagesBundle.getMessages().get("emptyName");
        }
        if(password == null || "".equals(password)) {
            errorMessage = messagesBundle.getMessages().get("emptyPassword");
        }
        if(email == null || "".equals(email)) {
            errorMessage = messagesBundle.getMessages().get("emptyEmail");
        }
        if(errorMessage != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.html");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);

        }
        else {
            User user = new User();
            user.setRoleId(2);
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);

            try {
                userService.addUser(user);
                log.info("User registered with email: " + email);
                User regUser = userService.getUserBy(user.getEmail(), user.getPassword());
                List allUsers = userService.getAllUsers();
                HttpSession session = request.getSession(true);
                session.setAttribute("user", regUser);
                session.setAttribute("allUsers", allUsers);
                response.sendRedirect("/home.jsp");
            } catch (DAOException e) {
                log.error("Database connection problem", e);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.html");
                errorMessage = messagesBundle.getMessages().get("registrationFailed");
                request.setAttribute("errorMessage", errorMessage);
                dispatcher.include(request, response);
            } catch (SQLException e) {
                log.error("Cannot execute SQL", e);
            }
        }

    }
}
