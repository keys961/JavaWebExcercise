<%@ page import="java.sql.Connection" %>
<%@ page import="org.yejt.contract.Utils" %>
<%@ page import="org.yejt.contract.DatabaseContract" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/27 0027
  Time: 21:56
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
        String query = "SELECT * FROM problem";
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
    <a href="/MainPage?page=question&pageId=1"><button class="btn btn-primary">First Page</button></a>
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
    <a href="/MainPage?page=question&pageId=<%=currentPage-1%>"><button class="btn btn-primary">Previous</button></a>
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
    <a href="/MainPage?page=question&pageId=<%=currentPage+1%>"><button class="btn btn-primary">Next</button></a>
    <%
        }
    %>
    <a href="/MainPage?page=question&pageId=<%=totalPage%>"><button class="btn btn-primary">Last Page</button></a>
</div>
<div class="list-group">
    <a class="list-group-item active" href="#">Questions</a>
    <div class="list-group-item">
        <%
            try
            {
                set.absolute((currentPage - 1) * 25 + 1);

                for(int i = (currentPage - 1) * 25 + 1; i <= size; i++)
                {
        %>
        <a href="/Question?%id=<%=i%>">
            <h4 class="list-group-item-heading">
                Question <%=i%>
            </h4>
            <p class="list-group-item-text">
                <%=set.getString(2)%>
            </p>
        </a>
        <%
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                connection.close();
                set.close();
                statement.close();
            }
        %>
    </div>
</div>
