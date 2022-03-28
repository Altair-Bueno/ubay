<%@ page import="uma.taw.ubay.auth.Auth" %>
<%@ page import="uma.taw.ubay.RequestKeys" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<br/>
<form method="post">
  <label>Username: <input name = "<%=Auth.USERNAME_PARAMETER%>" type="text" pattern="<%=Auth.USERNAME_REGEX%>"></label><br/>
  <label>Password: <input name = "<%=Auth.PASSWORD_PARAMETER%>" type="password" pattern="<%=Auth.PASSWORD_REGEX%>"></label>
  <input type="submit">
</form>
<%
    String cause = (String) request.getAttribute(RequestKeys.ERROR);
    if (cause != null) {
%>
<h1>ERROR: <%=cause%></h1>
<%}%>
</body>
</html>