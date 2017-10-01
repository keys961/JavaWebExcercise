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
    try
    {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM problem";
        set = statement.executeQuery(query);
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }
%>
<div class="list-group">
    <a class="list-group-item active" href="#">Questions</a>
    <div class="list-group-item">
        <a href="#">
            <h4 class="list-group-item-heading">
                Question 01
            </h4>
            <p class="list-group-item-text">
                Hello World!
            </p>
        </a>
    </div>
</div>
