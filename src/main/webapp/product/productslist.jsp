<%@ page import="uma.taw.ubay.entity.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.SessionKeys" %><%--
  Created by IntelliJ IDEA.
  User: franm
  Date: 28/3/22
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Productos</title>
</head>
<body>

    <%
        List<ProductEntity> l = (List<ProductEntity>) request.getAttribute("product-list");

    %>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Titulo</th>
            <th scope="col">Estado</th>
            <th scope="col">Descripcion</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(ProductEntity p : l){
        %>
        <tr>
            <td><img src="<%=p.getImages()%>" class="img-thumbnail" alt="<%=p.getTitle()%>"></td>
            <td><%=p.getTitle()%></td>
            <td><%=p.getCloseDate() == null ? "Abierto" : "Cerrado"%></td>
            <td><%=p.getDescription()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</body>
</html>
