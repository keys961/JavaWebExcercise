<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/10/2 0002
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.yejt.contract.Utils" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="org.yejt.contract.DatabaseContract" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page import="org.w3c.dom.NodeList" %><%--
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
    int id = Integer.parseInt(request.getParameter("id"));
    Connection connection = DatabaseContract.getConnection();
    Statement statement = null;
    ResultSet res = null;
    String title = "";
    String contentPath = "";
    NodeList bodyNodeList = null;
    String input = "";
    String output = "";
    try
    {
       statement = connection.createStatement();
       res = statement.executeQuery("SELECT * FROM problem WHERE id = " + id);
       if(res.next())
       {
           title = res.getString(2);
           contentPath = res.getString(3);
       }
       statement.close();
       res.close();
       connection.close();
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }

    try
    {
        String s = application.getRealPath("/");
        File file = new File(s + contentPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        bodyNodeList = doc.getElementsByTagName("body");
       // body = node.getTextContent();
        input = doc.getElementsByTagName("input").item(0).getTextContent();
        output = doc.getElementsByTagName("output").item(0).getTextContent();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

%>
<!DOCTYPE html>
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
            <h1>Question <%=id%>: <%=title%></h1>
            <hr>
            <h2>Description</h2>
            <!--Descriptions-->
            <br>
            <%
                for(int i = 0; i < bodyNodeList.getLength(); i++)
                {
            %>
            <p><%=bodyNodeList.item(i).getTextContent()%></p>
            <%
                }
            %>
            <hr>
            <h2>Input Specification</h2>
            <pre><%=input%></pre>
            <br>
            <h2>Output Specification</h2>
            <pre><%=output%></pre>
            <br>
            <h2>Limitation</h2>
            <p>Memory: 64MB, Time: 2000ms</p>
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
                                <option value="JAVAC">Java (javac 1.8)</option>
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
                            <button type="submit" class="btn btn-primary" id="submit">Submit</button>
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
<script>
    $(document).ready
    (
        function ()
        {
            $("#submit").click
            (
                function ()
                {
                        
                }
            );
        }
    );
</script>
</html>
