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
        String compiler = req.getParameter("compiler");
        Judge judge = null;
        Judge.Status res = null;
        switch (compiler)
        {
            case "GCC":
                judge = new CJudge(req.getParameter("code"));
                break;
            case "G++":
                judge = new CppJudge(req.getParameter("code"));
                break;
            case "JAVAC":
                judge = new JavaJudge(req.getParameter("code"));
                break;
        }

        if(judge != null)
            res = judge.judge();

        resp.getWriter().write(res.getMsg());
    }
}
