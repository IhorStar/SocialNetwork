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

@WebServlet("/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(DeleteCommentServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int comment = Integer.parseInt(request.getParameter("commentId"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");


        try {
            NewsDAO newsDAO = new NewsDAOImpl();
            CommentDAO commentDAO = new CommentDAOImpl();
            commentDAO.deleteCommentById(comment);
            List<News> allNews = newsDAO.getAllNews(user.getUserId());
            List<List<Comment>> allComment = commentDAO.getAllBy(allNews);
            session.setAttribute("allComment", allComment);
            response.sendRedirect("/home.jsp");
        } catch (DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", "Delete comment failed, please try again.");
            /*PrintWriter out = response.getWriter();
            out.println("<font color=red>Delete comment failed, please try again.</font>");
            */
            dispatcher.include(request, response);
        }
    }
}
