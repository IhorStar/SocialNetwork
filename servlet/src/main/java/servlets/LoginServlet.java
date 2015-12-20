package servlets;

import dao.DAOException;
import entity.Comment;
import entity.News;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import service.implementation.AuthorizationServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private UserService userService;
    private NewsService newsService;
    private CommentService commentService;
    private RelationService relationService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public void setRelationService(RelationService relationService) {
        this.relationService = relationService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter(("password"));
        AuthorizationService authorizationService = new AuthorizationServiceImpl();
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = null;

        if(email == null || "".equals("email")) {
            errorMessage = messagesBundle.getMessages().get("emptyEmail");
        }
        if(password == null || "".equals("password")) {
            errorMessage = messagesBundle.getMessages().get("emptyPassword");
        }
        if(errorMessage != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request,response);

        }
        else {
            try {
                User user = userService.getUserBy(email, password);
                if(user.getEmail() != null && user.getPassword() != null ) {
                    if(authorizationService.isUser(user)) {
                        List<News> allNews = newsService.getAllNews(user.getUserId());
                        List<List<Comment>> allComment = commentService.getAllBy(allNews);
                        List allRelation = relationService.getAllRelationBy(user.getUserId());
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        session.setAttribute("userService", userService);
                        session.setAttribute("allComment", allComment);
                        session.setAttribute("allRelation", allRelation);
                        response.sendRedirect("/home.jsp");
                    }
                    if(authorizationService.isAdmin(user)) {
                        List allUsers = userService.getAllUsers();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        session.setAttribute("allUsers", allUsers);
                        response.sendRedirect("/admin.jsp");
                    }
                }
                else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
                    errorMessage = messagesBundle.getMessages().get("emailOrPasswordInvalid");
                    request.setAttribute("errorMessage", errorMessage);
                    dispatcher.include(request, response);
                }
            } catch (DAOException e) {
                LOGGER.error("Database connection problem", e);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
                errorMessage = messagesBundle.getMessages().get("noUserFound");
                request.setAttribute("errorMessage", errorMessage);
                dispatcher.include(request, response);
            }
        }
    }
}
