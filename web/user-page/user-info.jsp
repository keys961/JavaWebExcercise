<%@ page import="org.yejt.contract.Utils" %><%--
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
    //TODO: Update User info
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
                    <img class="img-responsive" alt="<%%>" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cmVjdCBmaWxsPSIjMGQ4ZmRiIiB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgLz48dGV4dCBzdHlsZT0iZmlsbDojZmZmO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjEzcHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCIgdGV4dC1hbmNob3I9Im1pZGRsZSIgeD0iMTAwIiB5PSIxMDAiPjIwMHgyMDA8L3RleHQ+PC9zdmc+" data-src="holder.js/200x200/auto/sky">
                    <h3>Progress</h3>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img class="img-responsive" alt="" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cmVjdCBmaWxsPSIjMzlkYmFjIiB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgLz48dGV4dCBzdHlsZT0iZmlsbDojMUUyOTJDO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjEzcHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCIgdGV4dC1hbmNob3I9Im1pZGRsZSIgeD0iMTAwIiB5PSIxMDAiPjIwMHgyMDA8L3RleHQ+PC9zdmc+" data-src="holder.js/200x200/auto/vine">
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
