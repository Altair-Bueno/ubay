<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="uma.taw.ubay.entity.KindEnum" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
    if (entity != null && entity.getKind().equals(KindEnum.client)) {
%>
    <h1>Hello <%=entity.getUsername()%></h1>
    <form method="get" action="product/">
        <input type="submit" value="Productos">
    </form>
    <form method="post" action="auth/signoff">
        <input type="submit" value="Sign off">
    </form>

<%
    } else if(entity!= null && entity.getKind().equals(KindEnum.admin)){
%>
    <h1>Hello <%=entity.getUsername()%></h1>
    <form method="get" action="admin/users">
        <input type="submit" value="Manage users">
    </form>

    <form method="get" action="admin/categories">
        <input type="submit" value="Manage categories">
    </form>

    <form method="post" action="auth/signoff">
        <input type="submit" value="Sign off">
    </form>

<%
    } else {
%>
    <a href="auth/login">login</a>
    <a href="auth/register">Register</a>
    <a href="admin/users">admin/users</a>
<%
    }
%>
</body>
</html>