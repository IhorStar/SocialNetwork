package servlets;


import dao.DAOException;
import dao.RelationDAO;
import dao.implementation.RelationDAOImpl;
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

@WebServlet("/addToFriend")
public class AddToFriendServlet extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user2Id = Integer.parseInt(request.getParameter("user2Id"));
        int relationTypeId = Integer.parseInt(request.getParameter("relationType"));
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");

        try {
            RelationDAO relationDAO = new RelationDAOImpl();
            relationDAO.addRelation(user.getUserId(), user2Id,relationTypeId);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=green>Sending friend request success.</font>");
            dispatcher.include(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Sending friend request failed, please try again.</font>");
            dispatcher.include(request, response);
        }
    }
}
