package servlets;

import dao.DAOException;
import entity.User;
import internationalization.MessagesBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@WebServlet("/deleteNews")
public class DeleteNewsServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DeleteNewsServlet.class);
    private NewsService newsService;

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        MessagesBundle messagesBundle = new MessagesBundle();
        String errorMessage = messagesBundle.getMessages().get("deleteNewsFailed");


        try {
            newsService.deleteNewsById(newsId);
            List allNews = newsService.getAllNews(user.getUserId());
            session.setAttribute("allNews", allNews);
            response.sendRedirect("/home.jsp");
        }
        catch (DAOException e) {
            LOGGER.error("Database connection problem", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            request.setAttribute("errorMessage", errorMessage);
            dispatcher.include(request, response);
        }
    }
}
