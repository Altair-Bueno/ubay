<%@ page import="uma.taw.ubay.entity.CategoryEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
    <a href="../">Go home</a>

    <table border="1" id="categoryDataTable">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Delete category</th>
            <th>Modify category</th>
        </tr>
    <%
        List<CategoryEntity> catList = (List)request.getAttribute("category-list");
        if(catList != null){
            for(CategoryEntity c : catList){
    %>
    <tr>
        <td><%=c.getId()%></td>
        <td><%=c.getName()%></td>
        <td><%=c.getDescription()%></td>
        <td><a href="deleteCategory?id=<%=c.getId()%>">Delete category</a></td>
        <td><a href="modifyCategory?id=<%=c.getId()%>&name=<%=c.getName()%>&description=<%=c.getDescription()%>">Modify category</a></td>
    </tr>
    <%
            }
        }

    %>
        <br>

        <a href="addCategory">Add new category</a>


</body>
</html>
