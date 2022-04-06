<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete category</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/admin/categories" method="get">
        <h1>Are you sure you want to delete category with ID = <%=request.getParameter("id")%></h1>
        <input type="submit">
    </form>
</body>
</html>
