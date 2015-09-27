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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter(("password"));
        String errorMessage = null;

        if(email == null || "".equals("email")) {
            errorMessage = "Email cannot be empty";
        }
        if(password == null || "".equals("password")) {
            errorMessage = "Password cannot be empty";
        }
        if(errorMessage != null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMessage + "</font>");
            requestDispatcher.include(request,response);
        }
        else {
            try {
                UserDAO userDAO = new UserDAOImpl();
                User user = userDAO.getUserBy(email, password);
                if(user.getEmail() != null && user.getPassword() != null ) {
                    response.sendRedirect("/home.jsp");
                }
                else {
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>Email or password invalid.</font>");
                    requestDispatcher.include(request, response);
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }
}
