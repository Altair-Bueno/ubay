<%@ page import="uma.taw.ubay.entity.ProductEntity" %>
<%@ page import="uma.taw.ubay.servlet.product.ProductsList" %>
<%@ page import="uma.taw.ubay.dao.ProductFacade" %>
<%@ page import="uma.taw.ubay.entity.CategoryEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: franm
  Date: 29/3/22
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Actualizar producto</title>
</head>
<body>
    <%
        ProductEntity p = (ProductEntity) request.getAttribute("product");
        List<CategoryEntity> cats = (List<CategoryEntity>) request.getAttribute("cats");
    %>
    <form method="post" enctype="multipart/form-data">
        <div class="d-flex flex-row m-auto" style="width: 1000px">

            <%-- BLOQUE I - Imagen --%>
            <div class="d-flex flex-column p-2">
                <div class="p-2">
                    <img src="${pageContext.request.contextPath}/image?id=<%=URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8)%>" style="height: auto; width: 500px;" />
                </div>
                <div class="form-group mb-3 w-75 p-2">
                    <label for="img" class="form-label">Cambiar imagen: </label>
                    <input type="file" class="form-control" id="img" name="img" />
                </div>

            </div>

            <%-- BLOQUE II - Resto --%>
            <div class="d-flex flex-column p-2">
                <%-- Titulo --%>
                <div class="form-group w-75 p-2">
                    <label for="tit">Título: </label>
                    <input type="text" id="tit" class="form-control" name="titulo" value="<%=p.getTitle()%>"/>
                </div>

                <%-- Estado --%>
                <div class="p-2">
                    <label>Estado:</label>
                    <input type="radio" name="estado" value="Activo" <%=p.isCurrentlyAvailable() ? "checked" : ""%>> Activo </input>
                    <input type="radio" name="estado" value="Cerrado" <%=p.isCurrentlyAvailable() ? "" : "checked"%>> Cerrado </input>
                </div>

                <%-- Descripcion --%>
                <div class="p-2">
                    <label for="desc">Descripcion: </label>
                    <textarea id="desc" class="form-control" name="description" rows="4" cols="50"><%=p.getDescription()%></textarea>
                </div>

                <%-- Precio --%>
                <div class="p-2">
                    <label for="precio">Precio: </label>
                    <input type="text" id="precio" class="form-control" name="precio" value="<%=p.getOutPrice()%>"/>
                </div>

                <%-- Categoria --%>
                <div class="p-2">
                    <label>Categoria: </label>

                    <select name="categoria">
                        <%
                            for(CategoryEntity c : cats){

                        %>
                        <option value="<%=c.getId()%>" <%=p.getCategory().equals(c) ? "selected" : ""%> ><%=c.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <%-- Submit --%>
                <div class="p-2">
                    <input type="hidden" name="id" id="id" value="<%=p.getId()%>" />
                    <div class="d-flex flex-row p-2">
                        <div class="p-2">
                            <input class="btn btn-primary p-2" type="submit" value="Confirmar">
                        </div>
                        <div class="p-2">
                            <input class="btn btn-secondary p-2" type="submit" value="Cancelar" formaction="item?id=<%=p.getId()%>">
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </form>

</body>
</html>
