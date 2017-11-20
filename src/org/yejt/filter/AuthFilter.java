package org.yejt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();

        if(session == null || servletRequest.getAttribute("username") == null)
            ((HttpServletResponse)servletResponse).sendRedirect("/Login");
        else
            filterChain.doFilter(servletRequest, servletResponse);

    }
}
