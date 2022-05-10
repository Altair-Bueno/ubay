<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.products.ProductCategoryDTO" %>
<%@ page import="uma.taw.ubay.dto.products.ProductClientDTO" %>
<%--
Created by IntelliJ IDEA.
  User: franm
  Date: 28/3/22
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Nuevo producto</title>
</head>
<body>

    <%
        List<ProductCategoryDTO> cats = (List<ProductCategoryDTO>) request.getAttribute("cats");
        ProductClientDTO user = ((ProductClientDTO) request.getAttribute("user"));
    %>

    <%@include file="../WEB-INF/components/navbar.jsp"%>

    <form method="post" enctype="multipart/form-data">
        <div class="d-flex flex-row m-auto" style="width: 1000px">

            <%-- BLOQUE I - Imagen --%>
            <div class="d-flex flex-column p-2">
                <div class="p-2">
                    <img id="output" style="height: auto; width: 500px;" />
                </div>
                <div class="form-group mb-3 w-75 p-2">
                    <label for="img" class="form-label">Subir imagen: </label>
                    <input type="file" accept="image/*" class="form-control" id="img" name="img" onchange="loadFile(event)" />
                </div>

            </div>

            <%-- BLOQUE II - Resto --%>
            <div class="d-flex flex-column p-2">
                <%-- Titulo --%>
                <div class="form-group w-75 p-2">
                    <label for="tit">Título: </label>
                    <input type="text" id="tit" class="form-control" name="titulo" required/>
                </div>

                <%-- Descripcion --%>
                <div class="p-2">
                    <label for="desc">Descripcion: </label>
                    <textarea id="desc" class="form-control" name="description" rows="4" cols="50"></textarea>
                </div>

                <%-- Precio --%>
                <div class="p-2">
                    <label for="precio">Precio: </label>
                    <input type="number" id="precio" class="form-control" name="precio" required/>
                </div>

                <%-- Categoria --%>
                <div class="p-2">
                    <label>Categoria: </label>
                    <select name="categoria" required>
                        <%
                            for(ProductCategoryDTO c : cats){
                        %>
                        <option value="<%=c.getId()%>"><%=c.getName()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <%-- Submit --%>
                <div class="p-2">
                    <div class="d-flex flex-row p-2">
                        <div class="p-2">
                            <input type="text" name="vendor" hidden value="<%=user.getId()%>">
                            <input class="btn btn-primary p-2" type="submit" value="Confirmar">
                        </div>
                        <div class="p-2">
                            <input class="btn btn-secondary p-2" type="submit" value="Cancelar" formaction="<%=request.getHeader("Referer")%>" formnovalidate>
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
