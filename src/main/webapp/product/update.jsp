<%@ page import="uma.taw.ubay.entity.ProductEntity" %>
<%@ page import="uma.taw.ubay.servlet.product.ProductsList" %>
<%@ page import="uma.taw.ubay.dao.ProductFacade" %>
<%@ page import="uma.taw.ubay.entity.CategoryEntity" %>
<%@ page import="java.util.List" %><%--
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
    <form method="post">
        <div>
            <h1>Título: </h1>
            <textarea id="titulo" name="description" rows="4" cols="30"> <%=p.getTitle()%> </textarea>
        </div>

        <div>
            <img src=<%=p.getImages()%> />
            <h2>Cambiar foto (link): </h2>
            <input type="text" name="titulo" value=<%=p.getImages()%>/>
        </div>

        <div>
            <h2>Estado:</h2>
            <input type="radio" name="estado" value="Activo" <%=p.getCloseDate() == null ? "checked" : ""%>> Activo </input>
            <input type="radio" name="estado" value="Cerrado" <%=p.getCloseDate() == null ? "" : "checked"%>> Cerrado </input>
        </div>
        <div>
            <h2>Descripcion: </h2>
            <textarea id="description" name="description" rows="4" cols="50"> <%=p.getDescription()%> </textarea>
        </div>
        <div>
            <h2>Price: </h2>
            <input type="text" name="precio" value=<%=p.getOutPrice()%>>
        </div>
        <div>
            <h2>Categoria: </h2>
            <select name="cat" id="categoria">
                <option value="null">-</option>
                <%
                    for(CategoryEntity c : cats){

                    %>
                        <option value=<%=c.getId()%>><%=c.getName()%></option>
                <%
                    }
                %>
            </select>
        </div>

        <input hidden type="text" id="id" value=<%=p.getId()%> />
        <input type="submit" value="Editar">
    </form>



</body>
</html>
