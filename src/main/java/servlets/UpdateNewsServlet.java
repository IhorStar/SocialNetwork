package servlets;



import dao.DAOException;
import dao.NewsDAO;
import dao.implementation.NewsDAOImpl;
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


@WebServlet("/updateNews")
public class UpdateNewsServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UpdateNewsServlet.class);

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        News news = new News();
        news.setUserId(user.getUserId());
        news.setNewsId(Integer.parseInt(request.getParameter("newsId")));
        news.setDescription(request.getParameter("description"));

        try {
            NewsDAO newsDAO = new NewsDAOImpl();
            newsDAO.updateNews(news);
            List allNews = newsDAO.getAllNews(user.getUserId());
            session.setAttribute("allNews", allNews);
            response.sendRedirect("/home.jsp");
        }
        catch(DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", "Update news failed, please try again.");
            /*PrintWriter out = response.getWriter();
            out.println("<font color=red>Update news failed, please try again.</font>");
            */
            dispatcher.include(request, response);
        }
    }
}
