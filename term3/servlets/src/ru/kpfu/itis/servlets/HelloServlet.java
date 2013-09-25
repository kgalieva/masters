package ru.kpfu.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

            String username = request.getParameter("username");
            request.setAttribute("message", "Hello " + username);
            getServletConfig().getServletContext().getRequestDispatcher(
                    "/WEB-INF/jsp/hello.jsp").forward(request, response);

    }
}
