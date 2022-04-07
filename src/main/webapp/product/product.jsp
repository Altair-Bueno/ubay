<%@ page import="uma.taw.ubay.entity.ProductEntity" %><%--
  Created by IntelliJ IDEA.
  User: franm
  Date: 6/4/22
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Producto</title>
</head>
<body>
  <%
    ProductEntity p = (ProductEntity) request.getAttribute("product");
  %>

  <script>document.title = "Ubay | <%= p.getTitle()%>"</script>

  <div class="d-flex flex-row">
    <div class="p-2"><img src="<%=p.getImages()%>" class="rounded" alt="<%=p.getTitle()%>" style="height: 500px; width: 500px;"></div>
    <div class="d-flex flex-column p-2">
      <div class="p-2"><h1><%=p.getTitle()%></h1></div>
      <div class="p-2"><h1><%=p.getOutPrice()%> €</h1></div>
      <div class="p-2">
        <h2>Estado: </h2>
        <h4><%= p.getCloseDate() == null ? "Activo" : "Cerrado"%></h4>
      </div>
      <div class="p-2">
        <h2>Descripcion: </h2>
        <h6><%=p.getDescription()%></h6>
      </div>
      <div class="p-2">
        <form method="get" action="buy">
          <input type='hidden' name='id' id='id-compra' value=<%=p.getId()%> />
          <input class="btn btn-primary" type="submit" value="Comprar">
        </form>
      </div>
    </div>
    <div class="p-4">
        <form method="get" action="update">
          <input type='hidden' name='id' id='id-editar' value=<%=p.getId()%> />
          <input class="btn btn-secondary" type="submit" value="Editar">
        </form>
    </div>
  </div>

</body>
</html>
