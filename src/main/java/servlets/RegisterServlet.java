package servlets;

import dao.DAOException;
import dao.UserDAO;
import dao.implementation.UserDAOImpl;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String errorMessage = null;

        if(name == null || "".equals(name)) {
            errorMessage = "Name cannot be empty.";
        }
        if(password == null || "".equals(password)) {
            errorMessage = "Password cannot be empty.";
        }
        if(email == null || "".equals(email)) {
            errorMessage = "Email cannot be empty.";
        }
        if(errorMessage != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.html");
            request.setAttribute("errorMessage", errorMessage);
            /*PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMessage + "</font>");
            */
            dispatcher.include(request, response);

        }
        else {
            UserDAO userDAO = new UserDAOImpl();
            User user = new User();
            user.setRoleId(2);
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);

            try {
                userDAO.addUser(user);
                log.info("User registered with email: " + email);
                User regUser = userDAO.getUserBy(user.getEmail(), user.getPassword());
                List allUsers = userDAO.getAllUsers();
                HttpSession session = request.getSession(true);
                session.setAttribute("user", regUser);
                session.setAttribute("allUsers", allUsers);
                response.sendRedirect("/home.jsp");
            } catch (DAOException e) {
                log.error("Database connection problem", e);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.html");
                request.setAttribute("errorMessage","Registration failed, please try again.");
                /*PrintWriter out = response.getWriter();
                out.println("<font color=red>Registration failed, please try again.</font>");
                */
                dispatcher.include(request, response);
            }
        }

    }
}
