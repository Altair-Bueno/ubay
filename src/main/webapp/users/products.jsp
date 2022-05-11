<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
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
    <title>Ubay | Mis productos</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp"%>
<a class="btn btn-primary m-2" href=".." role="button">Go home</a>
    <div class="container">
        <h2>Favourite products:</h2>
        <div class="col">
            <%
                List<ProductDTO> l = (List<ProductDTO>) request.getAttribute("favourite-products-list");
            %>

            <table class="table table-bordered text-center">
                <thead>
                <tr>
                    <th scope="col">Image</th>
                    <th scope="col">Title</th>
                    <th scope="col">State</th>
                    <th scope="col">Description</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(ProductDTO p : l){
                %>
                <tr onclick="window.location='${pageContext.request.contextPath}/product/product?id=' + <%=p.getId()%>">
                    <td><img src="<%=p.getImages()%>" class="img-thumbnail" alt="<%=p.getTitle()%>" style="width: 200px"></td>
                    <td class="align-middle"><h3><%=p.getTitle()%></h3></td>
                    <td class="align-middle"><%=p.getCloseDate() == null ? "Abierto" : "Cerrado"%></td>
                    <td class="align-middle"><%=p.getDescription()%></td>

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
