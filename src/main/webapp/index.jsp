<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="uma.taw.ubay.entity.KindEnum" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>JSP - Hello World</title>
</head>
<body>
<%
    LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
    if (entity != null && entity.getKind().equals(KindEnum.client)) {
%>
    <h1>Hello <%=entity.getUsername()%></h1>
    <h2>Your role: <%=entity.getKind()%></h2>
    <form method="get" action="product/productslist">
        <input type="submit" value="Productos">
    </form>
    <form method="post" action="auth/signoff">
        <input type="submit" value="Sign off">
    </form>

<%
    } else if(entity!= null && entity.getKind().equals(KindEnum.admin)){
        response.sendRedirect(request.getContextPath() + "/admin/");
%>

    <%--<h1>Hello <%=entity.getUsername()%></h1>
    <h2>Your role: <%=entity.getKind()%></h2>
    <form method="get" action="users">
        <input type="submit" value="Manage users">
    </form>

    <form method="get" action="categories">
        <input type="submit" value="Manage categories">
    </form>

    <form method="post" action="auth/signoff">
        <input type="submit" value="Sign off">
    </form>--%>

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