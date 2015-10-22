package servlets;

import dao.CommentDAO;
import dao.DAOException;
import dao.NewsDAO;
import dao.implementation.CommentDAOImpl;
import dao.implementation.NewsDAOImpl;
import entity.Comment;
import entity.News;
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

        try {
            NewsDAO newsDAO = new NewsDAOImpl();
            CommentDAO commentDAO = new CommentDAOImpl();
            commentDAO.addComment(comment);
            List<News> allNews = newsDAO.getAllNews(user.getUserId());
            List<List<Comment>> allComments = commentDAO.getAllBy(allNews);
            session.setAttribute("allComments", allComments);
            response.sendRedirect("/home.jsp");
        }
        catch (DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", "Add comment failed, please try again.");
            /*PrintWriter out = response.getWriter();
            out.println("<font color=red>Add comment failed, please try again.</font>");
            */
            dispatcher.include(request, response);
        }
    }
}
