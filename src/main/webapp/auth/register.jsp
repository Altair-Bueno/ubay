<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page import="uma.taw.ubay.entity.GenderEnum" %><%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 28/3/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form method="post" action="${pageContext.request.contextPath}/auth/register">
    <label>Username:
        <input
                name="<%=AuthKeys.USERNAME_PARAMETER%>" type="text"
                pattern="<%=AuthKeys.USERNAME_REGEX%>" required></label><br/>
    <br/>
    <label>Password:
        <input
                name="<%=AuthKeys.PASSWORD_PARAMETER%>" type="password"
                pattern="<%=AuthKeys.PASSWORD_REGEX%>" required></label>
    <br/>
    <label>First Name:
        <input
                name="<%=AuthKeys.NAME_PARAMETER%>" type="text" required>
    </label>
    <br/>
    <label>Last Name:
        <input
                name="<%=AuthKeys.LAST_NAME_PARAMETER%>" type="text" required>
    </label>
    <br/>
    <label>Address:
        <input
                name="<%=AuthKeys.ADDRESS_PARAMETER%>" type="text" required>
    </label>
    <br/>
    <label>City:
        <input
                name="<%=AuthKeys.CITY_PARAMETER%>" type="text" required>
    </label>
    <br/>
    <label>Gender:
        <select name="<%=AuthKeys.GENDER_PARAMETER%>" required>
            <% for (GenderEnum gender : GenderEnum.values()) {%>
                <option><%=gender.toString()%></option>
            <%}%>
        </select>
    </label>
    <br/>
    <label>Birth date:
        <input
                name="<%=AuthKeys.BIRTH_PARAMETER%>" type="date" required>
    </label>
    <br/>
    <input type="submit">
</form>
</body>
</html>
