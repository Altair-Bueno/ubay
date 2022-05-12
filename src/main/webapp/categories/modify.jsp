<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Modificar categoria</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp"%>

    <div class="d-flex flex-column align-items-center">
        <h1>Datos</h1>

        <div class="d-flex flex-column">
                <form action="modify" method="get">
                    <div class="form col">
                        <label>
                            <input hidden type="text" class="form-control" name="id" value="<%=request.getParameter("id")%>"> <br>
                            <input hidden name="edited" value="1"/>
                            Nombre: <input type="text" class="form-control" name="name" value="<%=request.getParameter("name")%>"> <br>
                            Descripción: <input type="text" class="form-control" name="description" value="<%=request.getParameter("description")%>"> <br>

                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Modificar</button>
                    <button type="button" class="btn btn-secondary mt-2" onclick="goBack()">Cancelar</button>
                </form>
        </div>
    </div>

<script>
    function goBack()
    {
        window.history.back();
    }
</script>
</body>
</html>
