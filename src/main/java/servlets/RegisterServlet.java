package servlets;

import dao.DAOException;
import dao.UserDAO;
import dao.implementation.UserDAOImpl;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMessage + "</font>");
            requestDispatcher.include(request, response);

        }
        else {
            UserDAO userDAO = new UserDAOImpl();
            User user = new User();
            user.setUserId(1);
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);

            try {
                userDAO.addUser(user);
                response.sendRedirect("/home.jsp");
            } catch (DAOException e) {
                e.printStackTrace();
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/register.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Registration failed, please try again.</font>");
                requestDispatcher.include(request, response);
            }
        }

    }
}
