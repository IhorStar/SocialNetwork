package servlets;

import dao.DAOException;
import entity.Comment;
import entity.News;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.CommentService;
import service.NewsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeleteCommentServlet.class);
    private NewsService newsService;
    private CommentService commentService;

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int comment = Integer.parseInt(request.getParameter("commentId"));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = messagesBundle.getMessages().get("deleteCommentFailed");


        try {
            commentService.deleteCommentById(comment);
            List<News> allNews = newsService.getAllNews(user.getUserId());
            List<List<Comment>> allComment = commentService.getAllBy(allNews);
            session.setAttribute("allComment", allComment);
            response.sendRedirect("/home.jsp");
        } catch (DAOException e) {
            LOGGER.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        }
    }
}
