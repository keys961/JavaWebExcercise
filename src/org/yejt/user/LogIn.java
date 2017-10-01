package org.yejt.user;

import com.sun.media.jfxmedia.logging.Logger;
import org.yejt.contract.DatabaseContract;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

@WebServlet("/Login")
public class LogIn extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        password = DatabaseContract.getMD5(username + password); //Single-way hash

        Connection connection = DatabaseContract.getConnection();
        String query = "select password from user where username = ?";
        PreparedStatement preparedStatement = null;

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String dbPwd = resultSet.getString(1);
                if(dbPwd.equals(password))
                {
                    setCookies(username, password, resp);
                    setSession(username, password, req);
                    resp.getWriter().print("<script>alert(\"Login success!\");" +
                            "window.location.href=\"/MainPage\";</script>");
                }
                else
                {
                    resp.getWriter().print("<script> alert(\"Username or password is not" +
                            " correct! \"); window.location.href=\"/Login\"; </script>");
                }
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }
        catch (SQLException e)
        {
            Logger.logMsg(Level.SEVERE.intValue(), "SQL Exception");
        }


    }

    private void setCookies(String username, String password, HttpServletResponse resp)
    {
        Cookie cookie = new Cookie("username", username);
        Cookie cookie1 = new Cookie("password", password);
        cookie.setMaxAge(100000);
        cookie1.setMaxAge(100000);
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
    }

    private void setSession(String username, String password, HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
    }
}
