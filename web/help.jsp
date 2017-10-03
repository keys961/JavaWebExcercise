<%@ page import="org.yejt.contract.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error/error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Help of OJ</title>
</head>
<body>
<%
    if(Utils.isLoggedIn(request))
    {
%>
<jsp:include page="template/navbar_logged_in.jsp"/>
<%
    }
    else
    {
%>
<jsp:include page="template/navbar.jsp"/>
<%
    }
%>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

        </div>
        <div>
            <div class="container">
                <div class="jumbotron">
                    <h1>
                        Help of OJ!
                    </h1>
                    <hr>
                    <h3>
                        Steps to submit your code:
                    </h3>
                    <p>
                        1. Choose your compiler/interpreter,
                    </p>
                    <p>
                        2. Copy your code into the text area,
                    </p>
                    <p>
                        3. Click "Submit" to submit your code and wait for the result.
                    </p>
                    <hr>
                    <h3>
                        Result that the OJ may return:
                    </h3>
                    <p>
                        1. AC: Accepted, your answer is correct.
                    </p>
                    <p>
                        2. WA: Wrong answer, your program generated wrong answers.
                    </p>
                    <p>
                        3. TLE: Time limit exceeded, your program ran too much time.
                    </p>
                    <p>
                        4. MLE: Memory limit exceeded, your program used too much memory.
                    </p>
                    <p>
                        5. RE: Runtime error, your program occurred runtime error.
                    </p>
                    <p>
                        6. OLE: Output limit exceeded, your program outputted too much content.
                    </p>
                    <p>
                        7. CE: Compilation error, your code could not pass compilation.
                    </p>
                    <p>
                        8. Unknown Error: Your program occurred unknown error.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="template/foot.jsp"/>
<jsp:include page="template/include.jsp"/>
</body>
</html>