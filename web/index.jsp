<%@ page import="org.yejt.contract.Utils" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="org.yejt.contract.DatabaseContract" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/20 0020
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error/error.jsp" %>
<%
    //HttpSession httpSession = request.getSession();
    if(Utils.isLoggedIn(request))
        response.getWriter().print("<script>window.location.href=\"/MainPage\";</script>");
    else
    {
        String username = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        Connection connection = DatabaseContract.getConnection();
        Statement statement = connection.createStatement();
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals("username"))
                username = cookie.getValue();
            if(cookie.getName().equals("password"))
                password = cookie.getValue();
        }
        if(username != null && password != null)
        {
            ResultSet resultSet = statement.executeQuery("SELECT password FROM user WHERE username = \'" + username + "\'");
            if (resultSet.next())
            {
                if (password.equals(resultSet.getString(1)))
                {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("username", username);
                    httpSession.setAttribute("password", password);
                    httpSession.setMaxInactiveInterval(3600);
                    response.getWriter().print("<script>window.location.href=\"/MainPage\";</script>");
                }
            }
            resultSet.close();
        }
        connection.close();
        statement.close();
    }
%>
<html>
  <head>
    <title>OJ</title>
  </head>
  <body>
  <jsp:include page="./template/navbar.jsp"/>
  <div class="container">
    <div class="row clearfix">
      <div class="col-md-12 column">

      </div>
      <div>
        <div class="container">
          <div class="jumbotron">
            <h1>
              Hello! Welcome to my OJ!
            </h1>
            <p id="p">
               If you do not have an account here, please click the button below to sign up!
            </p>
            <p>
              <a class="btn btn-primary btn-lg" href="/Register">Sign Up NOW!</a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <hr>
  <br>
  <jsp:include page="template/foot.jsp"/>


  </body>

  <jsp:include page="template/include.jsp"/>
  <script type="text/javascript" src="./plugin/jquery-3.2.1.min.js"></script>
  <script>
    //alert($("#p").text());
  </script>
</html>
