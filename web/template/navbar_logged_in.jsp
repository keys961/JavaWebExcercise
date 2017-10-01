<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/23 0023
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String)request.getSession().getAttribute("username");
%>
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
        <ul class="nav navbar-nav">
            <li class="active">
                <a href="#"><%=username%></a>
            </li>
            <li>
                <a href="/Logout">Logout</a>
            </li>
        </ul>
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
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h2>
                <br />
            </h2>
        </div>
    </div>
</div>
