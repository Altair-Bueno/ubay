<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="newUser">
    <label>Username: <input name = "username" type="text"></label>
    <label>Password: <input name = "password" type="text"></label>
    <input type="submit">
</form>
</body>
</html>