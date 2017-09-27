<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/23 0023
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<jsp:include page="./template/navbar.jsp"/>
<div class="container">
    <div class="row clearfix">
        <div>
            <div class="container">
                <div class="jumbotron">
                    <div class="container">
                        <form class="form-signin" method="POST" action="/Login">
                            <h1 class="form-signin-heading">Sign In</h1>
                            <hr />
                            <div class="form-group">
                                <label for="id_login">Username:</label>
                                <input class="form-control" autofocus="autofocus" id="id_login" name="username" placeholder="Username" type="text" required />
                            </div>
                            <div class="form-group">
                                <label for="id_password">Password:</label>
                                <input class="form-control" id="id_password" name="password" placeholder="Password" type="password" required />
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">Sign In</button>
                            </div>
                            <hr>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<jsp:include page="template/foot.jsp"/>


</body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
