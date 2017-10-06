package org.yejt.user;

import org.yejt.contract.DatabaseContract;
import org.yejt.judge.CJudge;
import org.yejt.judge.CppJudge;
import org.yejt.judge.JavaJudge;
import org.yejt.judge.Judge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatSymbolsProvider;

@WebServlet("/Question")
public class Question extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/user-page/problem-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date sqlDate = new Date(date.getTime());
        java.util.Date date = new java.util.Date();

        String username = (String)req.getSession().getAttribute("username");
        String compiler = req.getParameter("compiler");
        int pid = Integer.parseInt(req.getParameter("pid"));
        Judge judge = null;
        Judge.Status res = null;
        switch (compiler)
        {
            case "GCC":
                judge = new CJudge(req.getParameter("code"), username, pid);
                break;
            case "G++":
                judge = new CppJudge(req.getParameter("code"), username, pid);
                break;
            case "JAVAC":
                judge = new JavaJudge(req.getParameter("code"), username, pid);
                break;
            default:
                judge = new CppJudge(req.getParameter("code"), username, pid);
        }


        res = judge.judge();

        Connection connection = DatabaseContract.getConnection();
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement("INSERT INTO submit VALUES (?,?,?,?,?)");
            statement.setString(1, username);
            statement.setInt(2, pid);
            statement.setInt(3, res.getStatusCode());
            statement.setObject(4, sdf.format(date));
            statement.setString(5, compiler);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        resp.getWriter().write(res.getMsg());
    }



}
