<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 30/3/22
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/admin/users" method="get">
        <h1>Are you sure you want to delete client with ID = <%=request.getParameter("id")%></h1>
            <input type="submit">
    </form>
</body>
</html>
