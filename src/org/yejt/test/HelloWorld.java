package org.yejt.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorld extends HttpServlet
{
    private String message;

    @Override
    public void init() throws ServletException
    {
        super.init();
        message = "Hello World";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(message);
    }

    @Override
    public void destroy()
    {
        super.destroy();
    }
}
