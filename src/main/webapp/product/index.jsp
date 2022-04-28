<%@ page import="uma.taw.ubay.entity.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="uma.taw.ubay.ProductKeys" %><%--
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
<%
    if(session.getAttribute(SessionKeys.LOGIN_CREDENTIALS) != null){
%>
<style>
    tr{
        cursor: pointer
    }

    tr:hover{
        background-color: #F5F5F5;
    }
</style>
<%
    }
%>
<body>

    <%
        List<ProductEntity> l = (List<ProductEntity>) request.getAttribute("product-list");
        String pagAtt = request.getParameter("page");
        int pagenum = pagAtt == null ? 1 : Integer.parseInt(pagAtt);
        int tam = (int) request.getAttribute("product-tam");
        int pagelimit = (int) Math.ceil((double) tam/ProductKeys.productsPerPageLimit);
    %>
    <%@include file="../WEB-INF/components/navbar.jsp"%>
    <div class="mx-auto" style="width: 1500px;">
        <%
            if(session.getAttribute(SessionKeys.LOGIN_CREDENTIALS) != null){
        %>

        <form method="get" action="${pageContext.request.contextPath}/product/new">
            <div class="py-3" style="width: max-content; float: left">
                <button type="submit" class="btn btn-success">Nuevo producto</button>
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
                for(ProductEntity p : l){
                    String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
                    if(navsesion == null){
            %>
                <tr>
            <%} else {
            %>
                <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=' + <%=p.getId()%>">
            <%
                    }
            %>
                    <td><img src="<%=imgSrc%>" class="img-thumbnail" alt="<%=p.getTitle()%>" style="width: 200px"></td>
                    <td class="align-middle"><h3><%=p.getTitle()%></h3></td>
                    <td class="align-middle"><%=p.isCurrentlyAvailable() ? "Abierto" : "Cerrado"%></td>
                    <td class="align-middle"><%=p.getDescription()%></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <form method="get" action="${pageContext.request.contextPath}/product">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <%
                        for(int n = 1; n <= pagelimit; n++){
                    %>
                    <li class="page-item <%=n == pagenum ? "active" : "" %>">
                        <input type="submit" class="page-link" aria-checked="<%=pagenum == n%>" name="page" value="<%=n%>">
                    </li>
                    <%
                        }
                    %>
                </ul>
            </nav>
        </form>
    </div>
</body>
</html>
