<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
    if (entity != null) {
%>
    <h1>Hello <%=entity.getUsername()%></h1>
    <form method="post" action="auth/signoff">
        <input type="submit" value="Sign of">
    </form>
<%
    } else {
%>
    <a href="auth/login">login</a>
    <a href="auth/register">Register</a>
<%
    }
%>
</body>
</html>