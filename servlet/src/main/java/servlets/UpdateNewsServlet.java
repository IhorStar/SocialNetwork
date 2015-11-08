package servlets;

import dao.DAOException;
import entity.News;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.NewsService;
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
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = messagesBundle.getMessages().get("updateNewsFailed");

        try {
            NewsService newsService = new NewsServiceImpl();
            newsService.updateNews(news);
            List allNews = newsService.getAllNews(user.getUserId());
            session.setAttribute("allNews", allNews);
            response.sendRedirect("/home.jsp");
        }
        catch(DAOException e) {
            log.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        }
    }
}