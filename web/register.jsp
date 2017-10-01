<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/23 0023
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">OJ</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="/Help">Help</a>
                        </li>
                        <li>
                            <a href="#">    </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h2>
                        <br />
                    </h2>
                </div>
            </div>
        </div>
        <div>
            <div class="container">
                <div class="jumbotron">
                    <div class="container">
                        <form class="form-signin" method="POST" onsubmit="myCheckForm()" action="/Register" >
                            <h1 class="form-signin-heading">Sign Up</h1>
                            <hr />
                            <div class="form-group">
                                <label for="id_reg">Username:</label>
                                <input class="form-control" autofocus="autofocus" id="id_reg" name="id_reg" placeholder="Username" type="text" required />
                            </div>
                            <div class="form-group">
                                <label for="id_password">Password:</label>
                                <input class="form-control" id="id_password" name="password_reg" placeholder="Password" type="password" required />
                            </div>
                            <div class="form-group">
                                <label for="id_password">Confirm your password:</label>
                                <input class="form-control" id="id_password_again" name="password_reg_again" placeholder="Password Again" type="password" required />
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">Sign Up</button>
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
<jsp:include page="template/include.jsp"/>
<script type="javascript">
    //TODO: Cannot use jQuery
    function myCheckForm() //Cannot use!!
    {
        //alert("Test")
        var username = document.getElementById("id_reg").textContent;
        var password = document.getElementById("id_password").textContent;
        var pwdAgain = document.getElementById("id_password_again").textContent;

        if(username.length >= 20)
        {
            alert("The length of username cannot be exceed 20!");
            return false;
        }

        if(password.length < 6 && password.length > 20)
        {
            alert("The length of password should be in range of 6 to 19 (inclusive)!");
            return false;
        }

        if(password != pwdAgain)
        {
            alert("2 passwords are not the same!");
            return false;
        }
        return true;
    }
</script>
</body>

</html>
