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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/updateComment")
public class UpdateCommentServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UpdateCommentServlet.class);
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
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Comment comment = new Comment();
        comment.setUserId(user.getUserId());
        comment.setNewsId(Integer.parseInt(request.getParameter("news")));
        comment.setText(request.getParameter("text"));
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = messagesBundle.getMessages().get("updateCommentFailed");

        try {
            commentService.updateComment(comment);
            List<News> allNews = newsService.getAllNews(user.getUserId());
            List<List<Comment>> allComment = commentService.getAllBy(allNews);
            session.setAttribute("allComment", allComment);
            response.sendRedirect("/home.jsp");
        } catch (DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        } catch (SQLException e) {
            log.error("Cannot execute SQL", e);
        }


    }
}
