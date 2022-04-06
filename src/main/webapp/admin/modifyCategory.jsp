<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify category</title>

    <a href="${pageContext.request.contextPath}/admin/categories" >Go back</a>
    <h1>Data: </h1>

    <form action="${pageContext.request.contextPath}/admin/modifyCategory" method="get">
        <label>
            ID: <input type="text" name="id" value="<%=request.getParameter("id")%>"> <br>
            Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"> <br>
            Description: <input type="text" name="description" value="<%=request.getParameter("description")%>"> <br>
        </label>
        <input type="submit">
    </form>
</head>
<body>

</body>
</html>
