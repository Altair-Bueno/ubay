<%@ page import="uma.taw.ubay.entity.ProductEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.entity.ClientEntity" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
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

    <!-- Bootstrap icons -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"
    />

    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <title>Ubay | Producto</title>
</head>
<body>
<%
    ClientEntity user = ((LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS)).getUser();
    ProductEntity p = (ProductEntity) request.getAttribute("product");
    String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
%>

<script>document.title = "Ubay | <%= p.getTitle()%>"</script>

<div class="d-flex flex-column" style="width: 1600px">
    <div class="p-2">
        <form method="get" action="${pageContext.request.contextPath}/product">
            <button type="submit" class="btn btn-labeled btn-light">
                <span class="btn-label"><i class="bi bi-arrow-left"></i></span>Volver
            </button>
        </form>
    </div>
    <div class="d-flex flex-row m-auto p-2">
        <div class="p-2"><img src="<%=imgSrc%>" class="rounded" alt="<%=p.getTitle()%>"
                              style="height: 500px; width: 500px;"></div>
        <div class="d-flex flex-column p-2">
            <div class="p-2"><h1><%=p.getTitle()%>
            </h1></div>
            <div class="p-2"><h1><%=p.getOutPrice()%> €</h1></div>
            <div class="p-2">
                <h2>Estado: </h2>
                <h4><%= p.isCurrentlyAvailable() ? "Activo" : "Cerrado"%>
                </h4>
            </div>
            <div class="p-2" style="height: 200px">
                <h2>Descripcion: </h2>
                <h6><%=p.getDescription()%>
                </h6>
            </div>
            <div class="p-2">
                <form method="get" action="buy">
                    <input type='hidden' name='id' id='id-compra' value="<%=p.getId()%>"/>
                    <input class="btn btn-primary" type="submit" value="Comprar"/>
                </form>
            </div>
        </div>
        <div class="p-4">
            <%
                if (user.getId() == p.getVendor().getId()) {
            %>

            <!-- EDITAR -->
            <div class="d-flex flex-row">
                <form method="get" action="${pageContext.request.contextPath}/product/update">
                    <input type='hidden' name='id' value="<%=p.getId()%>"/>
                    <input class="btn btn-secondary btn-block me-2" type="submit" value="Editar">
                </form>
                <!-- BORRAR: Button trigger modal -->
                <button type="button" class="btn btn-danger btn-block" data-bs-toggle="modal" data-bs-target="#deleteModal" style="height: 38px">
                    Eliminar
                </button>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Eliminar producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            ¿Está seguro de que quiere eliminar el producto?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <form method="post" action="${pageContext.request.contextPath}/product/delete">
                                <input type='hidden' name='id' value="<%=p.getId()%>"/>
                                <input class="btn btn-danger" type="submit" value="Eliminar">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <%
                }
            %>
        </div>
    </div>

</div>


</body>
</html>
