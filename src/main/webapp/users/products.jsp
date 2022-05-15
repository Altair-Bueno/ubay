<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.users.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 8/4/22
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Productos favoritos</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp" %>
<div class="container mt-4">
    <div class="row">
        <h1>Productos favoritos</h1>
    </div>
    <div class="row">
        <%
            List<ProductDTO> l = (List<ProductDTO>) request.getAttribute("favourite-products-list");
        %>

        <table class="table table-bordered text-center">
            <thead>
            <tr>
                <th scope="col">Imagen</th>
                <th scope="col">Título</th>
                <th scope="col">Estado</th>
                <th scope="col">Descripcion</th>
                <th scope="col">Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (ProductDTO p : l) {
            %>
            <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=' + <%=p.getId()%>">
                <td><img src="<%=p.getImages()%>" class="img-thumbnail" alt="<%=p.getTitle()%>" style="width: 200px">
                </td>
                <td class="align-middle"><h3><%=p.getTitle()%>
                </h3></td>
                <td class="align-middle"><%=p.getCloseDate() == null ? "Abierto" : "Cerrado"%>
                </td>
                <td class="align-middle"><%=p.getDescription()%>
                </td>

                <td class="align-middle">
                    <form method="get" action="${pageContext.request.contextPath}/users/deleteFavourite">
                        <input type='hidden' name='productID' value="<%=p.getId()%>"/>
                        <input type='hidden' name='clientID' value="<%=request.getAttribute("clientID")%>"/>
                        <button class="btn btn btn-outline-danger btn-labeled" type="submit">
                            <span><i class="bi bi-star-fill"></i></span>Eliminar de favoritos
                        </button>
                    </form>
                </td>

            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
