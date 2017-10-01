package org.yejt.user;

import org.yejt.contract.DatabaseContract;
import org.yejt.contract.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

//Get info & change pwd
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/user-page/user-info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String originalPassword = req.getParameter("originalPassword");
        String newPassword = req.getParameter("newPassword");

        HttpSession session = req.getSession();

        PrintWriter out = resp.getWriter();

        String query = "SELECT password FROM user where username = ?";
        String update = "UPDATE user SET password = ? WHERE username = ?";
        PreparedStatement statement = null;
        ResultSet res = null;
        if(!username.equals(session.getAttribute("username")))
        {
            out.print("<script>alert(\"Username incorrect!\");" +
                    "window.location.href=\"/UserInfo\";</script>");
        }
        else
        {
            Connection connection = DatabaseContract.getConnection();
            try
            {
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                res = statement.executeQuery();
                if(!res.next())
                    out.print("<script>alert(\"Username incorrect!\");" +
                            "window.location.href=\"/UserInfo\";</script>");
                else
                {
                    String pwd = res.getString(1);
                    if(!pwd.equals(Utils.getMD5(username + originalPassword)))
                        out.print("<script>alert(\"Original password incorrect!\");" +
                                "window.location.href=\"/UserInfo\";</script>");
                    else
                    {
                        statement = connection.prepareStatement(update);
                        statement.setString(1, Utils.getMD5(username + newPassword));
                        statement.setString(2, username);
                        if(statement.executeUpdate() > 0)
                        {
                            //String username = req.getCookies()[0].getName();
                            Cookie cookie = new Cookie("username", null);
                            cookie.setMaxAge(0);
                            Cookie cookie1 = new Cookie("password", null);
                            cookie1.setMaxAge(0);
                            resp.addCookie(cookie);
                            resp.addCookie(cookie1);
                            session.removeAttribute("username");
                            session.removeAttribute("password");
                            out.print("<script>alert(\"Change password success! Trying to log out!\");" +
                                    "window.location.href=\"/\";</script>");
                        }
                        else
                        {
                            out.print("<script>alert(\"Change password error!\");" +
                                    "window.location.href=\"/UserInfo\";</script>");
                        }
                    }
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                out.print("<script>alert(\"Error occurred!\");" +
                        "window.location.href=\"/UserInfo\";</script>");
            }
            finally
            {
                try
                {
                    connection.close();
                    statement.close();
                    res.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
