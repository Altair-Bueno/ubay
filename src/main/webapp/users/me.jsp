<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 8/4/22
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Me</title>
</head>
<body>
<div class="container">
    <div class="row">
        <%
            LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
            if (entity != null) {
        %>
        <h2>Profile: <%=entity.getUsername()%>
        </h2>
        <h4>Your role is: <%=entity.getKind()%>
        </h4>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
