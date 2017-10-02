<%@ page import="org.yejt.contract.Utils" %>
<%@ page import="org.yejt.contract.DatabaseContract" %>
<%@ page import="javax.xml.transform.Result" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/10/1 0001
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(!Utils.isLoggedIn(request))
        response.getWriter().print("<script> alert(\"You have not logged" +
                " in! \"); window.location.href=\"/Login\"; </script>");
%>

<%
    Connection connection = DatabaseContract.getConnection();
    PreparedStatement statement = null;
    Statement statement1 = null;
    ResultSet resultSet = null;
    int totalProblemNum = 0;
    int finishedProblem = 0;
    int totalUser = 0;
    int rank = 0;
    try
    {
        String query = "SELECT COUNT(DISTINCT pid) FROM submit WHERE username = ? AND status = 0";
        statement = connection.prepareStatement(query);
        statement.setString(1, (String)request.getSession().getAttribute("username"));
        resultSet = statement.executeQuery();
        if(resultSet.next())
            finishedProblem = resultSet.getInt(1);

        query = "SELECT COUNT(DISTINCT id) FROM problem";
        statement1 = connection.createStatement();
        resultSet = statement1.executeQuery(query);
        //resultSet = statement.executeQuery();
        if(resultSet.next())
            totalProblemNum = resultSet.getInt(1);

        query = "SELECT COUNT(DISTINCT username) FROM user";
        statement1 = connection.createStatement();
        resultSet = statement1.executeQuery(query);
        if(resultSet.next())
            totalUser = resultSet.getInt(1);

        query = "SELECT * FROM\n" +
                "  (SELECT username, @curRank := @curRank + 1 AS rank\n" +
                "   FROM\n" +
                "     (SELECT user.username, COUNT(DISTINCT pid) AS count\n" +
                "      FROM user LEFT JOIN\n" +
                "        (SELECT * FROM submit WHERE status = 0) valid_submit\n" +
                "          ON user.username = valid_submit.username\n" +
                "      GROUP BY (user.username) DESC) AS ranklist,\n" +
                "     (SELECT @curRank := 0) q) p\n" +
                "where username = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, (String)request.getSession().getAttribute("username"));
        resultSet = statement.executeQuery();
        if(resultSet.next())
            rank = resultSet.getInt(2);

        connection.close();
        statement.close();
        statement1.close();
        resultSet.close();
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }
%>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<jsp:include page="/template/navbar_logged_in.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Dash Board</h1>

            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <label>
                        <h4><%=finishedProblem%>/<%=totalProblemNum%></h4>
                    </label>
                    <hr>
                    <h3>Progress</h3>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <label>
                        <h4><%=rank%>/<%=totalUser%></h4>
                    </label>
                    <hr>
                    <h3>Rank</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Change Password</h1>
        </div>
            <div class="container col-sm-offset-3 col-md-offset-2">
                <form class="form-signin" method="POST" action="/UserInfo">
                    <div class="form-group">
                        <label for="id_login">Username:</label>
                        <input class="form-control" autofocus="autofocus" id="id_login" name="username" placeholder="Username" type="text" required />
                    </div>
                    <div class="form-group">
                        <label for="id_password">Original Password:</label>
                        <input class="form-control" id="id_password" name="originalPassword" placeholder="Original Password" type="password" required />
                    </div>
                    <div class="form-group">
                        <label for="id_password">Original Password:</label>
                        <input class="form-control" id="id_password_new" name="newPassword" placeholder="New Password" type="password" required />
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" type="submit">Change</button>
                    </div>
                    <hr>
                </form>
            </div>
    </div>
</div>
<jsp:include page="/template/foot.jsp"/>
<jsp:include page="/template/include.jsp"/>
</body>
</html>
