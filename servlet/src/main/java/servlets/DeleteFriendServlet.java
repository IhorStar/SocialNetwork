package servlets;

import dao.DAOException;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.RelationService;
import service.implementation.RelationServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(DeleteFriendServlet.class);

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user2Id = Integer.parseInt(request.getParameter("user2Id"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        MessagesBundle messagesBundle = new MessagesBundle();
        String successMessage = messagesBundle.getMessages().get("cancelFriendshipSuccess");
        String errorMessage = messagesBundle.getMessages().get("cancelFriendshipFailed");

        try {
            RelationService relationService = new RelationServiceImpl();
            relationService.deleteRelationBy(user.getUserId(), user2Id);
            List allRelation = relationService.getAllRelationBy(user.getUserId());
            session.setAttribute("allRelation", allRelation);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("successMessage", successMessage);
            dispatcher.include(request, response);
        } catch (DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        }
    }
}