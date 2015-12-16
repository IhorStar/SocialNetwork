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

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(DeleteUserServlet.class);
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int ADMIN = 1;
        int userId = Integer.parseInt(request.getParameter("userId"));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = messagesBundle.getMessages().get("deleteUserFailed");

        try {
            userService.deleteUserById(userId);
            List allUsers = userService.getAllUsers();
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
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
        }
    }
}
