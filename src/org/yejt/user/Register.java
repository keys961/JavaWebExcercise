package org.yejt.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import com.sun.istack.internal.Nullable;
import org.yejt.contract.DatabaseContract;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/Register")
public class Register extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       // this.doPost(req, resp);
        //retransmit request to a jsp page
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        String username = req.getParameter("id_reg");
        String password = req.getParameter("password_reg");
        String selectCmd = "select username from user where username = ?";
        Connection connection = DatabaseContract.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if(connection != null)
        {
            try
            {
               statement = connection.prepareStatement(selectCmd);
               statement.setString(1, username);
               resultSet = statement.executeQuery();
               if(!resultSet.next())
               {
                   String insertCmd = "insert into user values (?, ?, 1)";
                   statement = connection.prepareStatement(insertCmd);
                   statement.setString(1, username);
                   statement.setString(2, DatabaseContract.getMD5(username + password));
                   int i = statement.executeUpdate();
                   if(i > 0)
                        resp.getWriter().print("<script> alert(\"Register successfully!" +
                           " \"); window.location.href=\"/Login\"; </script>");
                   //resp.sendRedirect("/login.jsp");
               }
               else
               {
                   resp.getWriter().print("<script> alert(\"Username has already" +
                           " been registered. \"); window.location.href=\"/Register\"; </script>");
                   //resp.sendRedirect("/register.jsp");
               }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


}
