package org.yejt.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Logout")
public class LogOut extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        //String username = req.getCookies()[0].getName();
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        Cookie cookie1 = new Cookie("password", null);
        cookie1.setMaxAge(0);
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
        session.removeAttribute("username");
        session.removeAttribute("password");

        resp.getWriter().print("<script>alert(\"Logout success!\"); window.location.href=" +
                "\"/index.jsp\";</script>");
    }
}
