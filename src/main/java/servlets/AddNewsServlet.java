package servlets;

import dao.DAOException;
import dao.NewsDAO;
import dao.implementation.NewsDAOImpl;
import entity.News;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addNews")
public class AddNewsServlet extends HttpServlet {
    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        News news = new News();
        news.setUserId(user.getUserId());
        news.setDescription(request.getParameter("description"));

        try {
            NewsDAO newsDAO = new NewsDAOImpl();
            newsDAO.addNews(news);
            response.sendRedirect("/home.jsp");
        } catch (DAOException e) {
            e.printStackTrace();
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Save news failed, please try again.</font>");
            requestDispatcher.include(request, response);
        }
    }
}
