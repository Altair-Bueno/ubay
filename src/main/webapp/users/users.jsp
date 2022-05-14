<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.users.ClientDTO" %>
  Created by IntelliJ IDEA.
  User: jota
  Date: 28/3/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Usuarios</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp" %>
<div>
    <%--<form>
        <h3>User data:</h3>
        ID: <input type="text" name="id"> <br>
        Name: <input type="text" name="name"> <br>
        Last name: <input type="text" name="lastName"> <br>
        Address: <input type="text" name="address"> <br>
        City: <input type="text" name="city"> <br>
        Gender: <select id="gender" name="gender">
            <option id="--" name="--">--</option>
            <option id="male" name="male">male</option>
            <option id="female" name="female">female</option>
            <option id="other" name="other">other</option>
        </select> <br/>
        <input type="submit"/>
    </form>--%>

    <div class="container">
        <h1>Buscar usuarios</h1>
        <div class="row">
            <div class="col-3">
                <form>
                    <div class="form col">
                        ID: <input type="text" class="form-control" id="id" name="id">
                        Nombre: <input type="text" class="form-control" id="name" name="name">
                        Apellidos: <input type="text" class="form-control" id="lastName" name="lastName">
                        Dirección: <input type="text" class="form-control" id="address" name="address">
                        Ciudad: <input type="text" class="form-control" id="city" name="city" aria-describedby="city">
                        Género: <select class="form-select" id="gender" name="gender">
                        <option selected value="--">--</option>
                        <option value="male">Masculino</option>
                        <option value="female">Femenino</option>
                        <option value="other">Otro</option>
                    </select>
                        <button type="submit" class="btn btn-primary mt-2">Confirmar</button>
                    </div>
                </form>
            </div>

            <div class="col">
                <table class="table table-responsive" id="userDataTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Género</th>
                        <th>Dirección</th>
                        <th>Ciudad</th>
                        <th>Fecha de Nacimiento</th>
                        <th>Eliminar usuario</th>
                        <th>Modificar user</th>
                        <th>Reestablecer contraseña</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<ClientDTO> searchClient = (List)request.getAttribute("search-user");

                        if(searchClient != null){
                            for(ClientDTO c : searchClient){

                    %>
                    <tr>
                        <td><%=c.getId()%>
                        </td>
                        <td><%=c.getName()%>
                        </td>
                        <td><%=c.getLastName()%>
                        </td>
                        <td><%=c.getGender()%>
                        </td>
                        <td><%=c.getAddress()%>
                        </td>
                        <td><%=c.getCity()%>
                        </td>
                        <td><%=c.getBirthDate()%>
                        </td>
                        <td><a href=delete?id=<%=c.getId()%>>Eliminar usuario</a></td>
                        <td>
                            <a href="modify?id=<%=c.getId()%>&name=<%=c.getName()%>&lastName=<%=c.getLastName()%>&gender=<%=c.getGender()%>&address=<%=c.getAddress()%>&city=<%=c.getCity()%>&birthDate=<%=c.getBirthDate()%>">Modificar
                                usuario</a></td>
                        <td><a href="passwordChangeLink?id=<%=c.getId()%>">Reestablecer contraseña</a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/auth/register" role="button">Registrar
                    a un usuario nuevo</a>
            </div>
        </div>
    </div>

</div>
</body>
</html>
