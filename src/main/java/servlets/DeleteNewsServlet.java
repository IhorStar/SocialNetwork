package servlets;

import dao.DAOException;
import dao.NewsDAO;
import dao.implementation.NewsDAOImpl;
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
import java.util.List;

@WebServlet("/deleteNews")
public class DeleteNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");


        try {
            NewsDAO newsDAO = new NewsDAOImpl();
            newsDAO.deleteNewsById(newsId);
            List allNews = newsDAO.getAllNews(user.getUserId());
            session.setAttribute("allNews", allNews);
            response.sendRedirect("/home.jsp");
        }
        catch (DAOException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Delete news failed, please try again.</font>");
            dispatcher.include(request, response);

        }
    }
}
