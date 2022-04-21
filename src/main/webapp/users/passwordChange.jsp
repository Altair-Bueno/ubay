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
    <title>Title</title>
</head>
<body>
    <%
        String passwordChangeID = (String) request.getAttribute("passwordChangeID");
        String username = (String) request.getAttribute("username");
    %>
    <a href="${pageContext.request.contextPath}/auth/resetPassword?passwordChangeID=<%=URLEncoder.encode(passwordChangeID,StandardCharsets.UTF_8)%>&username=<%=URLEncoder.encode(username,StandardCharsets.UTF_8)%>">This is the link</a>
</body>
</html>
