package ru.kpfu.itis.servlets.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ParametersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        // 1. Параметры запроса
        request.getParameter("username");

        // 2. Заголовки запроса
        request.getHeader("Host");

        //3. Cookies
        //достаем cookies
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("JSESSIONID")) {
                sessionId = cookie.getValue();
            }
        }

        //создаем новую cookie
        Cookie cookie = new Cookie("userName", "Peter");
        /*Время жизни = 24 часа.
        * Чтобы удалить cookie, достаточно передать методу setMaxAge() значение 0
        * */
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        //4. Http сессия
        HttpSession session = request.getSession();
        session.setAttribute("sessionParam", "value");
        Object value = session.getAttribute("param");

        //5. ServletContext
        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("contextParam", "value");
        value = context.getAttribute("param");

    }
}
