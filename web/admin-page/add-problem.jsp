<%@ page import="java.sql.Connection" %>
<%@ page import="org.yejt.contract.DatabaseContract" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Yejt
  Date: 2017/10/14 0014
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Connection connection = DatabaseContract.getConnection();
    Statement statement = connection.createStatement();
    ResultSet set = statement.executeQuery("SELECT COUNT(id) FROM problem");
    set.next();
    int id = set.getInt(1) + 1;
%>
<div class="list-group">
    <a class="list-group-item active" href="#">Add A Question</a>
    <div class="list-group-item">
        <form class="form-horizontal" enctype="multipart/form-data" role="form" method="post" action="/AdminHome/AddProblem">
            <div class="form-group">
                <label for="id" class="col-sm-2 control-label">Problem ID</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="id" name="id" value="<%=id%>">
                </div>
            </div>
            <div class="form-group">
                <label for="title" class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="title" name="title" placeholder="Please input problem title" required>
                </div>
            </div>
            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Description</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="description" id="description" cols="110" rows="40" wrap="hard" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="input" class="col-sm-2 control-label">Input Specification</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="input" id="input" cols="110" rows="20" wrap="hard" style="font-family: monospace" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="output" class="col-sm-2 control-label">Output Specification</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="output" id="output" cols="110" rows="20" wrap="hard" style="font-family: monospace" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="testcase" class="col-sm-2 control-label">Test Cases</label>
                <div class="col-sm-10">
                    <input type="file" name="testcase" id="testcase" class="form-control" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="answer" class="col-sm-2 control-label">Answer</label>
                <div class="col-sm-10">
                    <input type="file" name="answer" id="answer" class="form-control" required/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>