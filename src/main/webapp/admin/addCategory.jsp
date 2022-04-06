<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add category:</title>
    <a href="${pageContext.request.contextPath}/admin/categories" >Go back</a>

    <form action="${pageContext.request.contextPath}/admin/addCategory" method="get">
        <label>
            Name: <input type="text" name="name"> <br>
            Description: <input type="text" name="description"> <br>
        </label>
        <input type="submit">
    </form>
</head>
<body>

</body>
</html>
