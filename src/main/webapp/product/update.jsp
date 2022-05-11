<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="uma.taw.ubay.dto.products.ProductDTO" %>
<%@ page import="uma.taw.ubay.dto.products.ProductCategoryDTO" %><%--
  Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
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
    <title>Ubay | Actualizar producto</title>
</head>
<body>
    <%
        ProductDTO p = (ProductDTO) request.getAttribute("product");
        List<ProductCategoryDTO> cats = (List<ProductCategoryDTO>) request.getAttribute("cats");
        String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
    %>
    <%@include file="../WEB-INF/components/navbar.jsp"%>

    <form method="post" enctype="multipart/form-data">
        <div class="d-flex flex-row m-auto" style="width: 1000px">

            <%-- BLOQUE I - Imagen --%>
            <div class="d-flex flex-column p-2">
                <div class="p-2">
                    <img src="<%=imgSrc%>" id="output" style="height: auto; width: 500px;" />
                </div>
                <div class="form-group mb-3 w-75 p-2">
                    <label for="img" class="form-label">Cambiar imagen: </label>
                    <input type="file" accept="image/*" onchange="loadFile(event)" class="form-control" id="img" name="img" />
                </div>

            </div>

            <%-- BLOQUE II - Resto --%>
            <div class="d-flex flex-column p-2">
                <%-- Titulo --%>
                <div class="form-group w-75 p-2">
                    <label for="tit">Título: </label>
                    <input type="text" id="tit" class="form-control" name="titulo" value="<%=p.getTitle()%>" required/>
                </div>

                <%-- Estado --%>
                <div class="p-2">
                    <label>Estado:</label>
                    <input type="radio" name="estado" value="Activo" <%=p.getCloseDate() == null ? "checked" : ""%>> Activo </input>
                    <input type="radio" name="estado" value="Cerrado" <%=p.getCloseDate() == null ? "" : "checked"%>> Cerrado </input>
                </div>

                <%-- Descripcion --%>
                <div class="p-2">
                    <label for="desc">Descripcion: </label>
                    <textarea id="desc" class="form-control" name="description" rows="4" cols="50"><%=p.getDescription()%></textarea>
                </div>

                <%-- Precio --%>
                <div class="p-2">
                    <label for="precio">Precio: </label>
                    <input type="number" id="precio" class="form-control" name="precio" value="<%=p.getOutPrice()%>" required/>
                </div>

                <%-- Categoria --%>
                <div class="p-2">
                    <label>Categoria: </label>

                    <select name="categoria" required>
                        <%
                            for(ProductCategoryDTO c : cats){

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
                            <input class="btn btn-secondary p-2" type="submit" value="Cancelar" formaction="item?id=<%=p.getId()%>" formnovalidate>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </form>

</body>
<script>
    var loadFile = function(event) {
        var image = document.getElementById('output');
        image.src = URL.createObjectURL(event.target.files[0]);
    };
</script>
</html>
