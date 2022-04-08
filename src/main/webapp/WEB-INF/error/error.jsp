<%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 8/4/22
  Time: 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Ubay-<%=exception.getMessage()%></title>
</head>
<body>
<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <h1>An error has occurred</h1>
                <p><%=exception.getMessage()%></p>
                <a href="<%=request.getHeader("Referer")%>">Go back</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
