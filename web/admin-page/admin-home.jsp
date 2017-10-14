<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/10/14 0014
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cmd = request.getParameter("page");
    String ref = "";
    if(cmd == null || cmd.equals("add"))
        ref = "add-problem.jsp";
    else if(cmd.equals("modify"))
        ref = "modify-problem.jsp";
    else
        ref = "add-problem.jsp";
%>
<html>
<head>
    <title>Admin Home Page</title>
</head>
<body>
<jsp:include page="/template/navbar.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-3 col-lg-3">
            <ul class="nav nav-list well">
                <li class="active">
                    <a href="/AdminHome?page=add">Add Question</a>
                </li>
                <li>
                    <a href="/AdminHome?page=modify">Modify Question</a>
                </li>
            </ul>
        </div>
        <div class="col-md-9 col-lg-9">
            <jsp:include page="<%=ref%>"/>
        </div>
    </div>
</div>
<jsp:include page="/template/foot.jsp"/>
<jsp:include page="/template/include.jsp"/>
</body>
</html>
