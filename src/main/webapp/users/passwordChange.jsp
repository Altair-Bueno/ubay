<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 21/4/22
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Change password</title>
</head>
<body>
    <%
        String passwordChangeID = (String) request.getAttribute("passwordChangeID");
        String username = (String) request.getAttribute("username");
    %>
    <div class="col-6 position-absolute top-50 start-50 translate-middle">
        <h1>¿Estás seguro de que quieres cambiar la contraseña?</h1>
        <a class="btn btn-primary mt-2" href="${pageContext.request.contextPath}/auth/resetPassword?passwordChangeID=<%=URLEncoder.encode(passwordChangeID,StandardCharsets.UTF_8)%>&username=<%=URLEncoder.encode(username,StandardCharsets.UTF_8)%>">Confirmar</a>
    </div>


<%--    <a href="${pageContext.request.contextPath}/auth/resetPassword?passwordChangeID=<%=URLEncoder.encode(passwordChangeID,StandardCharsets.UTF_8)%>&username=<%=URLEncoder.encode(username,StandardCharsets.UTF_8)%>">This is the link</a>--%>
</body>
</html>
