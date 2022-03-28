<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<br/>
<%
    String cause = (String) request.getAttribute("cause");
    if (cause != null) {
%>
    <h1><%=cause%></h1>
<%}%>
<form method="post">
  <label>Username: <input name = "username" type="text"></label>
  <label>Password: <input name = "password" type="text"></label>
  <input type="submit">
</form>
</body>
</html>