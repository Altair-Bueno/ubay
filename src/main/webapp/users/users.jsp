<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.users.ClientDTO" %>
<%--
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
<%
    String id = request.getAttribute("id") == null ? "" : (String) request.getAttribute("id");
    String name = request.getAttribute("name") == null ? "" : (String) request.getAttribute("name");
    String lastName = request.getParameter("lastName") == null ? "" : (String) request.getAttribute("lastName");
    String address = request.getParameter("address") == null ? "" : (String) request.getAttribute("address");
    String city = request.getParameter("city") == null ? "" : (String) request.getAttribute("city");
    String gender = request.getParameter("gender") == null ? "" : (String) request.getAttribute("gender");
%>
<%@include file="../WEB-INF/components/navbar.jsp" %>
<div>
    <div class="container">
        <h1>Buscar usuarios</h1>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/auth/register" role="button">Registrar
            a un usuario nuevo</a>
        <div class="row">
            <div class="col-3">
                <form>
                    <div class="form col">
                        ID: <input type="number" class="form-control" id="id" name="id" value="<%=id%>" maxlength="5">
                        Nombre: <input type="text" class="form-control" id="name" name="name" value="<%=name%>" maxlength="10">
                        Apellidos: <input type="text" class="form-control" id="lastName" name="lastName" value="<%=lastName%>" maxlength="10">
                        Dirección: <input type="text" class="form-control" id="address" name="address" value="<%=address%>" maxlength="15">
                        Ciudad: <input type="text" class="form-control" id="city" name="city" aria-describedby="city" value="<%=city%>" maxlength="10">
                        Género: <select class="form-select" id="gender" name="gender">
                        <option <%=gender.equals("--") ? "selected" : ""%> value="--">--</option>
                        <option <%=gender.equals("male") ? "selected" : ""%> value="male">Masculino</option>
                        <option <%=gender.equals("female") ? "selected" : ""%> value="female">Femenino</option>
                        <option <%=gender.equals("other") ? "selected" : ""%> value="other">Otro</option>
                    </select>
                        <button type="submit" class="btn btn-primary mt-2">Buscar</button>
                        <a class="btn btn-secondary mt-2" href="<%=request.getContextPath()%>/users/">Limpiar</a>
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
            </div>
        </div>
    </div>

</div>
</body>
</html>
