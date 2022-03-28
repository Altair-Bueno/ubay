<%@ page import="uma.taw.ubay.auth.AuthKeys" %>
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
  <label>Username: <input name = "<%=AuthKeys.USERNAME_PARAMETER%>" type="text" pattern="<%=AuthKeys.USERNAME_REGEX%>"></label><br/>
  <label>Password: <input name = "<%=AuthKeys.PASSWORD_PARAMETER%>" type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>"></label>
  <input type="submit">
</form>
</body>
</html>