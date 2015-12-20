package servlets;

import dao.DAOException;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.RelationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addToFriend")
public class AddToFriendServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(AddToFriendServlet.class);
    private RelationService relationService;

    public void setRelationService(RelationService relationService) {
        this.relationService = relationService;
    }

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user2Id = Integer.parseInt(request.getParameter("user2Id"));
        int relationTypeId = Integer.parseInt(request.getParameter("relationType"));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        MessagesBundle messagesBundle = new MessagesBundle();
        String successMessage = messagesBundle.getMessages().get("friendRequestSuccess");
        String errorMessage = messagesBundle.getMessages().get("friendRequestFailed");

        try {
            relationService.addRelation(user.getUserId(), user2Id,relationTypeId);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("successMessage", successMessage);
            dispatcher.include(request, response);
        } catch (DAOException e) {
            LOGGER.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        }
    }
}
