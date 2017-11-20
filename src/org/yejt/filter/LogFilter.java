package org.yejt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogFilter implements Filter
{

    private final Logger logger = Logger.getGlobal();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        long startTime = System.currentTimeMillis();
        try
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally
        {
            long endTime = System.currentTimeMillis();
            HttpServletRequest in = (HttpServletRequest)servletRequest;
            HttpServletResponse out = (HttpServletResponse)servletResponse;

            String length = out.getHeader("Content-Length");
            if(length == null || length.length() == 0)
                length = "-";

            synchronized (logger)
            {
                HttpSession session = in.getSession();
                String username = "-";
                if(session.getAttribute("username") != null)
                    username = (String)session.getAttribute("username");
                logger.log(Level.INFO,
                        "Address: " + in.getRemoteAddr()
                + "; Port: " + in.getRemotePort()
                + "; Method: " + in.getMethod()
                + "; User: " + username
                + "; URI: " + in.getRequestURI()
                + "; Protocol: " + in.getProtocol()
                + "; Status: " + out.getStatus()
                + "; Header Length: " + length
                + "; Time: " + (endTime - startTime) + "ms.");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        //logger = ;
    }

    @Override
    public void destroy()
    {

    }
}
