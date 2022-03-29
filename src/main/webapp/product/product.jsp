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
    <title>Productos</title>
</head>
<body>

    <%
        List<ProductEntity> l = (List<ProductEntity>) request.getAttribute("product-list");
        for(ProductEntity p : l){
    %>

    <h1><%=p.getTitle()%> <%=p.getOutPrice()%></h1>
    <img src=<%=p.getImages()%> />
    <div>
        <h2>Estado: </h2>
        <h4><%= p.getCloseDate() == null ? "Activo" : "Cerrado"%></h4>
    </div>
    <div>
        <h2>Descripcion: </h2>
        <h6><%=p.getDescription()%></h6>
    </div>

    <%

        if(true){ // p.getVendor().equals(request.getSession().getAttribute(SessionKeys.LOGIN_CREDENTIALS))

    %>
    <form method="get" action="update" + >
        <input type='hidden' name='id' id='id' value=<%=p.getId()%> />
        <input type="submit" value="Editar">
    </form>
    <%
        }
    %>




    <%
        }
    %>

</body>
</html>
