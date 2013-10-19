package ru.kpfu.itis.login.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

            getServletConfig().getServletContext().getRequestDispatcher(
                    "/jsp/login.jsp").forward(request, response);


    }

    @Override
    public void doPost (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("abc") && password.equals("123")){
            request.setAttribute("error", false);
            response.sendRedirect("profile");
            request.getSession().setAttribute("session_uname", username);
        } else {
            request.setAttribute("error", true);
            getServletConfig().getServletContext().getRequestDispatcher(
                    "/jsp/login.jsp").forward(request, response);
        }

    }
}
