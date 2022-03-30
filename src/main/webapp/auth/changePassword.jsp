<%@ page import="uma.taw.ubay.AuthKeys" %><%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 30/3/22
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change your password</title>
</head>
<body>
    <h1>Change your password</h1>
    <form action="${pageContext.request.contextPath}/auth/changePassword" method="post">
        <label>
            Old password: <input type="password" name="<%=AuthKeys.OLD_PASSWORD_PARAMETER%>">
        </label>
        <br/>
        <label>
            New password: <input type="password" name="<%=AuthKeys.PASSWORD_PARAMETER%>">
        </label>
        <br/>
        <label>
            Repeat password: <input type="password" name="<%=AuthKeys.REPEAT_PASSWORD_PARAMETER%>">
        </label>
    </form>
</body>
</html>
