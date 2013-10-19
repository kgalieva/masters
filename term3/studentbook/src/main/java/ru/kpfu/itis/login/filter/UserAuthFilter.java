package ru.kpfu.itis.login.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// Implements Filter class
public class UserAuthFilter implements Filter {

    private ArrayList urlList;

    public void init(FilterConfig config) throws ServletException {
        // Read the URLs to be avoided for authentication check (From web.xml)
        String urls = config.getInitParameter("avoid-urls");
        urlList = new ArrayList();
        Collections.addAll(urlList, urls.split(","));
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getServletPath();
        boolean allowedRequest = false;
        String strURL = "";

        // To check if the url can be excluded or not
        for (int i = 0; i < urlList.size(); i++) {
            strURL = urlList.get(i).toString();
            if (url.startsWith(strURL)) {
                allowedRequest = true;
            }
        }

        if (!allowedRequest) {
            HttpSession session = request.getSession(false);
            if (session == null
                    || session.getAttribute("session_uname") == null) {
                // Forward the control to login.jsp if authentication fails
                request.getRequestDispatcher("/").forward(request,
                        response);
            }
        }
        chain.doFilter(req, res);
    }

    public void destroy() {
    }
} 