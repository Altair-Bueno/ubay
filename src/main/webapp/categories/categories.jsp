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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Categories</title>
</head>
<body>
    <a class="btn btn-primary m-2" href=".." role="button">Go home</a>

    <div class="container">
        <h2>Categories: </h2>
        <div class="row">
            <div class="col">
                <table class="table table-responsive" id="categoryDataTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Delete category</th>
                        <th>Modify category</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%
        List<CategoryEntity> catList = (List)request.getAttribute("category-list");
        if(catList != null){
            for(CategoryEntity c : catList){
    %>
                    <tr>
                        <td><%=c.getId()%></td>
                        <td><%=c.getName()%></td>
                        <td><%=c.getDescription()%></td>
                        <td><a href="delete?id=<%=c.getId()%>">Delete category</a></td>
                        <td><a href="modify?id=<%=c.getId()%>&name=<%=c.getName()%>&description=<%=c.getDescription()%>">Modify category</a></td>
                    </tr>
                        <%
            }
        }

    %>
                        </tbody>
                </table>
                    <br>
                <a class="btn btn-primary m-2" href="add" role="button">Add new category</a>
            </div>
        </div>
    </div>
</body>
</html>