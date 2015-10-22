package servlets;

import dao.DAOException;
import dao.RelationDAO;
import dao.implementation.RelationDAOImpl;
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

@WebServlet("/deleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(DeleteFriendServlet.class);

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user2Id = Integer.parseInt(request.getParameter("user2Id"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");

        try {
            RelationDAO relationDAO = new RelationDAOImpl();
            relationDAO.deleteRelationBy(user.getUserId(), user2Id);
            List allRelation = relationDAO.getAllRelationBy(user.getUserId());
            session.setAttribute("allRelation", allRelation);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("successMessage", "Cancel friendship success.");
            /*PrintWriter out = response.getWriter();
            out.println("<font color=green>Cancel friendship success.</font>");
            */
            dispatcher.include(request, response);
        } catch (DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", "Cancel friendship failed, please try again.");
            /*PrintWriter out = response.getWriter();
            out.println("<font color=green>Cancel friendship failed, please try again.</font>");
            */
            dispatcher.include(request, response);
        }
    }
}
