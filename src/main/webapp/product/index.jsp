<%@ page import="java.util.List" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="uma.taw.ubay.ProductKeys" %>
<%@ page import="uma.taw.ubay.dto.products.ProductCategoryDTO" %>
<%@ page import="uma.taw.ubay.dto.products.ProductDTO" %>
<%--
  Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
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

<style>
    tr {
        cursor: pointer
    }

    th {
        cursor: default;
    }

    tr:hover {
        background-color: #F5F5F5;
    }
</style>

<body>

<%
    List<ProductDTO> l = (List<ProductDTO>) request.getAttribute("product-list");
    List<ProductCategoryDTO> categories = (List<ProductCategoryDTO>) request.getAttribute("category-list");
    String pagAtt = request.getParameter("page");
    Object nameParameter = request.getAttribute("nameFilter");
    Object favOwnedParameter = request.getParameter("favOwnedFilter");
    boolean favFilter = false, ownedFilter = false;
    if(favOwnedParameter != null){
        String favOwned = (String) favOwnedParameter;
        if(favOwned.equals("favFilter")){
            favFilter = true;
        } else if(favOwned.equals("ownedFilter")){
            ownedFilter = true;
        }
    }
    String nameFilter = (nameParameter == null ? "" : (String) nameParameter);
    int categoryFilter = (int) request.getAttribute("categoryFilter");

    int pagenum = pagAtt == null ? 1 : Integer.parseInt(pagAtt);
    int tam = (int) request.getAttribute("product-tam");
    boolean loggedIn = (boolean) request.getAttribute("user");
    int pagelimit = (int) Math.ceil((double) tam / ProductKeys.productsPerPageLimit);
%>
<%@include file="../WEB-INF/components/navbar.jsp" %>

<div class="mx-auto" style="width: 1500px;">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <h2>Filtros:</h2>
                <form id="filterForm">
                    <div class="form col">
                        Nombre del producto: <input id="nameFilter" type="text" class="form-control" id="name"
                                                    name="name" value="<%=nameFilter%>" maxlength="20">
                        Categoría:
                        <select id="categoryFilter" class="form-select" id="category" name="category">
                            <option <%=categoryFilter == 0 ? "selected" : ""%>value="--">--</option>
                            <%
                                int i = 1;
                                for (ProductCategoryDTO cat : categories) {
                            %>
                            <option <%=categoryFilter == i ? "selected" : ""%> value="<%=cat.getId()%>"><%=cat.getName()%>
                            </option>
                            <%
                                    i++;
                                }
                            %>
                        </select>
                        <%
                            if(loggedIn){
                        %>

                        <input type="radio" id="favFilter" name="favOwnedFilter" value="favFilter" <%=favFilter ? "checked" : ""%>>
                        <label for="favFilter">Mis favoritos</label> <br>
                        <input type="radio" id="ownedFilter" name="favOwnedFilter" value="ownedFilter" <%=ownedFilter ? "checked" : ""%>>
                        <label for="favFilter">Mis productos</label> <br>
                        <%
                            }
                        %>

                        <button type="submit" class="btn btn-primary mt-2">Buscar</button>
                        <a class="btn btn-secondary mt-2" href="<%=request.getContextPath()%>/product">Limpiar</a>
                    </div>
                </form>
            </div>

            <div class="col">
                <%
                    if (loggedIn) {
                %>
                <form method="get" action="${pageContext.request.contextPath}/product/new">
                    <div class="py-3" style="width: max-content; float: left">
                        <button type="submit" class="btn btn-primary">Subir producto</button>
                    </div>
                </form>
                <%
                    }
                %>

                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th scope="col">Imagen</th>
                        <th scope="col">Titulo</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Descripcion</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (ProductDTO p : l) {
                            String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
                    %>
                    <tr>
                    <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=' + <%=p.getId()%>">

                        <td><img src="<%=imgSrc%>" class="img-thumbnail" alt="<%=p.getTitle()%>" style="width: 200px">
                        </td>
                        <td class="align-middle"><h3><%=p.getTitle()%>
                        </h3></td>
                        <td class="align-middle"><%=p.getCloseDate() == null ? "Abierto" : "Cerrado"%>
                        </td>
                        <td class="align-middle"><%=p.getDescription()%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <form method="get" action="${pageContext.request.contextPath}/product">
                    <%
                        if (categoryFilter != 0) {

                    %>
                    <input type="text" hidden name="category" value="<%=categoryFilter%>">
                    <%
                        }

                        if (!nameFilter.equals("")) {
                    %>
                    <input type="text" hidden name="name" value="<%=nameFilter%>">
                    <%
                        }
                        if(favFilter) {
                    %>
                    <input type="text" hidden name="favOwnedFilter" value="favFilter">
                    <%
                        } else if(ownedFilter) {
                    %>
                    <input type="text" hidden name="favOwnedFilter" value="ownedFilter">
                    <%
                        }
                    %>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <%
                                for (int n = 1; n <= pagelimit; n++) {
                            %>
                            <li class="page-item <%=n == pagenum ? "active" : "" %>">
                                <input type="submit" class="page-link" aria-checked="<%=pagenum == n%>" name="page"
                                       value="<%=n%>">
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </nav>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
