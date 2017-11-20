package org.yejt.admin;

import org.yejt.contract.DatabaseContract;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AdminHome/DeleteProblem")
public class DeleteProblem extends HttpServlet
{

    private static final String PATH = "E:\\Programming\\Java\\WebHelloWorld\\web"; //May change

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if(req.getParameter("id") == null)
            return;

        Connection connection = DatabaseContract.getConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        int id = Integer.parseInt(((String)req.getParameter("id")));
        try
        {
            statement = connection.prepareStatement("SELECT * FROM problem WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            String contentPath = PATH + File.separator + "problems" + File.separator + id + ".xml";
            String answerPath = PATH + File.separator + "testcase" + File.separator + id + ".txt";
            String testcasePath = PATH + File.separator + "answers" + File.separator + id + ".txt";

            File f;
            f = new File(contentPath);
            f.delete();
            f = new File(answerPath);
            f.delete();
            f = new File(testcasePath);
            f.delete();

            statement = connection.prepareStatement("DELETE FROM problem WHERE id = ?");
            statement.setInt(1, id);
            int n = statement.executeUpdate();
            if(n > 0)
                resp.getWriter().print("<script>alert(\"Delete Succeeded!\"); windows.location.href=\"/AdminHome\";</script>");
            else
                resp.getWriter().print("<script>alert(\"Delete Failed!\"); windows.location.href=\"/AdminHome\";</script>");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            resp.getWriter().print("<script>alert(\"Delete Failed!\"); windows.location.href=\"/AdminHome\";</script>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }
}
