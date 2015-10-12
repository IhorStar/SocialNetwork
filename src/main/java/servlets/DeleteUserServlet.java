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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int ADMIN = 1;
        int userId = Integer.parseInt(request.getParameter("userId"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");

        try {
            UserDAO userDAO = new UserDAOImpl();
            userDAO.deleteUserById(userId);
            List allUsers = userDAO.getAllUsers();
            session.setAttribute("allUsers", allUsers);
            if(user.getRoleId() == ADMIN) {
                response.sendRedirect("/admin.jsp");
            }
            else {
                session = null;
                response.sendRedirect("/login.html");
            }
        }
        catch (DAOException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Delete user failed, please try again.</font>");
            dispatcher.include(request, response);
        }
    }
}
