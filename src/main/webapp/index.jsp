<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<%
    LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute("login");
    if (entity != null) {
%>
    <h1><%=entity.getUsername()%></h1>
<%
    } else {
%>
    <a href="auth/login">login</a>
<%
    }
%>
</body>
</html>