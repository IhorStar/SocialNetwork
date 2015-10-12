package servlets;

import dao.*;
import dao.implementation.CommentDAOImpl;
import dao.implementation.NewsDAOImpl;
import dao.implementation.RelationDAOImpl;
import dao.implementation.UserDAOImpl;
import entity.Comment;
import entity.News;
import entity.User;
import service.AuthorizationService;
import service.implementation.AuthorizationServiceImpl;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter(("password"));
        String errorMessage = null;
        AuthorizationService authorizationService = new AuthorizationServiceImpl();

        if(email == null || "".equals("email")) {
            errorMessage = "Email cannot be empty";
        }
        if(password == null || "".equals("password")) {
            errorMessage = "Password cannot be empty";
        }
        if(errorMessage != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMessage + "</font>");
            dispatcher.include(request,response);
        }
        else {
            try {
                UserDAO userDAO = new UserDAOImpl();
                User user = userDAO.getUserBy(email, password);
                if(user.getEmail() != null && user.getPassword() != null ) {
                    if(authorizationService.isUser(user)) {
                        NewsDAO newsDAO = new NewsDAOImpl();
                        CommentDAO commentDAO = new CommentDAOImpl();
                        RelationDAO relationDAO = new RelationDAOImpl();
                        List<News> allNews = newsDAO.getAllNews(user.getUserId());
                        List<List<Comment>> allComment = commentDAO.getAllBy(allNews);
                        List allUser = userDAO.getAllUsers();
                        List allRelation = relationDAO.getAllRelationBy(user.getUserId());
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        session.setAttribute("userDAO", userDAO);
                        session.setAttribute("allComment", allComment);
                        session.setAttribute("allRelation", allRelation);
                        response.sendRedirect("/home.jsp");
                    }
                    if(authorizationService.isAdmin(user)) {
                        List allUser = userDAO.getAllUsers();
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        session.setAttribute("allUser", allUser);
                        response.sendRedirect("/admin.jsp");
                    }
                }
                else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>Email or password invalid.</font>");
                    dispatcher.include(request, response);
                }
            } catch (DAOException e) {
                e.printStackTrace();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>No user found with given email and password, please register first.</font>");
                dispatcher.include(request, response);
            }
        }
    }
}
