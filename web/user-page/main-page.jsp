<%@ page import="org.yejt.contract.Utils" %><%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/20 0020
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(!Utils.isLoggedIn(request))
        response.getWriter().print("<script> alert(\"You have not logged" +
                " in! \"); window.location.href=\"/Login\"; </script>");
%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<jsp:include page="/template/navbar_logged_in.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-3 col-lg-3">
            <ul class="nav nav-list well">
                <li class="active">
                    <a href="#">Questions</a>
                </li>
                <li>
                    <a href="#">Submit History</a>
                </li>
                <li>
                    <a href="#">Ranking</a>
                </li>
            </ul>
        </div>
        <div class="col-md-9 col-lg-9">
            <div class="list-group">
                <a class="list-group-item active" href="#">Questions</a>
                <div class="list-group-item">
                    <h4 class="list-group-item-heading">
                        Question 01
                    </h4>
                    <p class="list-group-item-text">
                        Hello World!
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/template/foot.jsp"/>
</body>
<jsp:include page="/template/include.jsp"/>
</html>
