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
import service.implementation.CommentServiceImpl;
import service.implementation.NewsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AddCommentServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Comment comment = new Comment();
        comment.setUserId(user.getUserId());
        comment.setNewsId(Integer.parseInt(request.getParameter("news")));
        comment.setText(request.getParameter("text"));
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = messagesBundle.getMessages().get("addCommentFailed");

        try {
            NewsService newsService = new NewsServiceImpl();
            CommentService commentService = new CommentServiceImpl();
            commentService.addComment(comment);
            List<News> allNews = newsService.getAllNews(user.getUserId());
            List<List<Comment>> allComments = commentService.getAllBy(allNews);
            session.setAttribute("allComments", allComments);
            response.sendRedirect("/home.jsp");
        }
        catch (DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        }
    }
}