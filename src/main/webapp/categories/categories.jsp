<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="uma.taw.ubay.dto.categories.CategoryDTO" %>
<%@ page import="uma.taw.ubay.dto.LoginDTO" %><%--
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
</head>
<title>Ubay | Categorias</title>
<body>
<%@include file="../WEB-INF/components/navbar.jsp" %>
<%
    LoginDTO loginDTO = (LoginDTO) session.getAttribute(SessionKeys.LOGIN_DTO);
    if (loginDTO != null && loginDTO.getKind().equals(KindEnum.admin)) {
%>
<div class="container">
    <h1>Categorías</h1>
    <div class="row">
        <div class="col">
            <table class="table table-responsive" id="categoryDataTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Borrar categoría</th>
                    <th>Modificar categoría</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<CategoryDTO> catList = (List) request.getAttribute("category-list");
                    if (catList != null) {
                        for (CategoryDTO c : catList) {
                %>
                <tr>
                    <td><%=c.getId()%>
                    </td>
                    <td><%=c.getName()%>
                    </td>
                    <td><%=c.getDescription()%>
                    </td>
                    <td><a href="delete?id=<%=c.getId()%>">Borrar categoría</a></td>
                    <td><a href="modify?id=<%=c.getId()%>&name=<%=c.getName()%>&description=<%=c.getDescription()%>">Modificar
                        categoría</a></td>
                </tr>
                <%
                        }
                    }

                %>
                </tbody>
            </table>
            <br>
            <a class="btn btn-primary m-2" href="add" role="button">Añadir una nueva categoría</a>
        </div>
    </div>
</div>

<%
} else if (loginDTO != null && loginDTO.getKind().equals(KindEnum.client)) {
%>

<div class="container">
    <h1>Categorías</h1>
    <div class="row">
        <div class="col">
            <table class="table table-responsive">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <% if (loginDTO != null) { %>
                    <th>Favorita</th>
                    <%}%>


                </tr>
                </thead>
                <tbody>
                <%
                    List<CategoryDTO> catList = (List) request.getAttribute("category-list");
                    if (catList != null) {
                        for (CategoryDTO c : catList) {
                %>
                <tr>
                    <td><%=c.getId()%>
                    </td>
                    <td><%=c.getName()%>
                    </td>
                    <td><%=c.getDescription()%>
                    </td>
                    <%
                        List<CategoryDTO> favouriteCategories = (List<CategoryDTO>) request.getAttribute("user-fav-category-list");
                        if (favouriteCategories.contains(c)) {
                    %>
                    <td>
                        <a href="deleteFavourite?categoryID=<%=c.getId()%>&clientID=<%=request.getAttribute("client-id")%>">Eliminar
                            de favoritos</a></td>
                    <%
                    } else {
                    %>
                    <td>
                        <a href="addFavourite?categoryID=<%=c.getId()%>&clientID=<%=request.getAttribute("client-id")%>">Añadir
                            a favoritos</a></td>
                    <%
                        }
                    %>
                </tr>
                <%
                            }
                        }
                    }

                %>
                </tbody>
            </table>
            <br>
        </div>
    </div>
</div>
</body>
</html>
