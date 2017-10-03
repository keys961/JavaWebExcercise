<%@ page import="org.yejt.contract.DatabaseContract" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.yejt.judge.Judge" %><%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/28 0028
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Connection connection = DatabaseContract.getConnection();
    ResultSet set = null;
    Statement statement = null;
    int totalPage = 0;
    int size = 0;
    try
    {
        statement = connection.createStatement();
        String query = "SELECT * FROM submit WHERE username = "+ "'" +
                (String)request.getSession().getAttribute("username") + "'"
                + " ORDER BY submit_time DESC";
        set = statement.executeQuery(query);
        set.last();
        size = set.getRow();
        totalPage = size / 25 + size % 25 == 0 ? 0 : 1;
        if(size == 0)
            totalPage = 1;
        //statement.close();
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }
    int currentPage = 1;
    if(request.getParameter("pageId") != null)
        currentPage = Integer.parseInt(request.getParameter("pageId"));

%>
<div class="pagination" align="right">
    <a href="/MainPage?page=history&pageId=1"><button class="btn btn-primary">First Page</button></a>
    <%
        if(currentPage == 1)
        {
    %>
    <a href="#"><button class="btn btn-primary" disabled>Previous</button></a>
    <%
    }
    else
    {
    %>
    <a href="/MainPage?page=history&pageId=<%=currentPage-1%>"><button class="btn btn-primary">Previous</button></a>
    <%
        }
    %>
    <%
        if(currentPage == totalPage)
        {
    %>
    <a href="#"><button class="btn btn-primary" disabled>Next</button></a>
    <%
    }
    else
    {
    %>
    <a href="/MainPage?page=history&pageId=<%=currentPage+1%>"><button class="btn btn-primary">Next</button></a>
    <%
        }
    %>
    <a href="/MainPage?page=history&pageId=<%=totalPage%>"><button class="btn btn-primary">Last Page</button></a>
</div>
<div class="list-group">
    <a class="list-group-item active" href="#">Submit History</a>
    <div class="list-group-item">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Username</th>
                <th>Problem ID</th>
                <th>Status</th>
                <th>Submit Date</th>
            </tr>
            </thead>
            <tbody>
            <%
                try
                {
                    set.absolute((currentPage - 1) * 25 + 1);
                    for(int i = (currentPage - 1) * 25 + 1; i <= size; i++)
                    {
            %>
            <tr>
                <td><%=set.getString(1)%></td>
                <td><%=set.getString(2)%></td>
                <td><%=Judge.setMsg(set.getInt(3))%></td>
                <td><%=set.getString(4)%></td>
            </tr>
            <%
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    statement.close();
                    set.close();
                    connection.close();
                }
            %>

            </tbody>
        </table>
    </div>
</div>
