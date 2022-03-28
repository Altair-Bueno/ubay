<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.entity.ClientEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<%
    LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
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