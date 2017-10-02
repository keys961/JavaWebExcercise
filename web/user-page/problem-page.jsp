<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/10/2 0002
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
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

<%

%>
<html>
<head>
    <title>Question</title>
</head>
<body>
<jsp:include page="/template/navbar_logged_in.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-3 col-lg-3">
            <ul class="nav nav-list well">
                <li class="active">
                    <a href="/MainPage?page=question">Questions</a>
                </li>
                <li>
                    <a href="/MainPage?page=history">Submit History</a>
                </li>
            </ul>
        </div>
        <div class="col-md-9 col-lg-9">
            <h1>Question : </h1>
            <hr>
            <h2>Description</h2>
            <!--Descriptions-->
            <hr>
            <h2>Input Specification</h2>
            <pre></pre>
            <br>
            <h2>Output Specification</h2>
            <pre></pre>
            <br>
            <h2>Limitation</h2>
            <p>Memory: 64MB, Time: 1000ms</p>
            <hr>
            <form class="">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-3 col-lg-3">
                            <label class="">Compiler/Interpreter</label>
                        </div>
                        <div class="col-md-9 col-lg-9">
                            <select name="compiler" class="form-control">
                                <option value="GCC">C (gcc 4.9.2)</option>
                                <option value="G++">C++ (g++ 4.9.2)</option>
                                <option value="JAVAC">Java (JDK 1.8)</option>
                                <option value="PYTHON2">Python 2 (python 2.7)</option>
                                <option value="PYTHON3">Python 3 (python 3.6)</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <label>Your code:</label>
                            <textarea name="code" id="code" cols="110" rows="40" wrap="hard" style="font-family: monospace"></textarea>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-lg-3">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                        <label id="status" style="font-size: large; margin-top: 6px; display: none">Status: Not Submitted</label>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<jsp:include page="/template/foot.jsp"/>
</body>
<jsp:include page="/template/include.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ext-language_tools.js" type="text/javascript" charset="utf-8"></script>
</html>
