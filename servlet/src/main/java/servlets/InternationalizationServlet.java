package servlets;

import internationalization.MessagesBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/I18N")
public class InternationalizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String country = request.getParameter("country");

        MessagesBundle messagesBundle = new MessagesBundle();
        Map<String, String> messages = messagesBundle.getMessages(language, country);

        HttpSession session = request.getSession();
        session.setAttribute("messages", messages);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        dispatcher.include(request, response);

    }
}
