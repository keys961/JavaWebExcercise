<%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/9/20 0020
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="../error/error.jsp" %>
<html>
<head>
    <title>Auto-generated Table</title>
</head>
<body>
<h1>Hello World</h1>
<%
    String[] names = {"Fuck", "Asshole"};
%>

<table border="1" align="center">
    <th>No.#</th>
    <th>Name</th>
    <%
        for(int i = 0; i < 2; i++)
        {
    %>
    <tr>
        <td><%=i%></td>
        <td><%=names[i]%></td>
    </tr>
    <%
        }
    %>
</table>

<jsp:include page="../template/foot.jsp"/>
</body>
</html>
