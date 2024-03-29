<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page import="uma.taw.ubay.entity.GenderEnum" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 30/3/22
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Modificar usuario</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp" %>

<div class="d-flex flex-column align-items-center">
    <div>
        <h1>Datos</h1>
    </div>

    <div class="d-flex flex-column">
        <form action="modify" method="get">
            <div class="form col">
                <input hidden type="text" class="form-control" name="id" value="<%=request.getParameter("id")%>"> <br>
                Nombre: <input required type="text" class="form-control" name="name" value="<%=request.getParameter("name")%>" maxlength="10">
                Apellidos: <input required type="text" class="form-control" name="lastName" value="<%=request.getParameter("lastName")%>" maxlength="10"> <br>
                Dirección: <input required type="text" class="form-control" name="address"
                                  value="<%=request.getParameter("address")%>" maxlength="15"> <br>
                <input hidden name="edited" value="1"/>
                Ciudad: <input required type="text" class="form-control" name="city" value="<%=request.getParameter("city")%>" maxlength="10">
                <br>
                Género: <select name="gender" class="form-select">
                <% for (GenderEnum gender : GenderEnum.values()){
                    String clientGender = request.getParameter("gender");
                    GenderEnum clientGenderEnum = GenderEnum.valueOf(clientGender);
                    if(gender.equals(clientGenderEnum)){
                %>
                <option selected><%=gender.toString()%></option>
                <%
                } else {
                %>
                <option><%=gender.toString()%></option>
                <%
                    }
                %>
                <%}%>
            </select> </br>
                Fecha de nacimiento: <input type="date" class="form-control" name="birthDate"
                                            value="<%=request.getParameter("birthDate")%>"> <br>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Modificar</button>
            <button type="button" class="btn btn-secondary mt-2" onclick="goBack()">Cancelar</button>

        </form>
    </div>
</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>
