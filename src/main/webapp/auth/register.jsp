<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page import="uma.taw.ubay.entity.GenderEnum" %><%--
  Created by IntelliJ IDEA.
  User: Altair Bueno
  Date: 28/3/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Registro</title>
</head>
<style>
    body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }

    .form-signin {
        width: 100%;
        max-width: 330px;
        padding: 15px;
        margin: auto;
    }
</style>
<body class="text-center">
<main class="form-signin">
    <h1 class="h3 mb-3 fw-normal">Registro en Ubay</h1>
    <form method="post" action="${pageContext.request.contextPath}/auth/register" class="row g-2">
        <div class="form-floating col-12">
            <input id="username" class="form-control" placeholder="Usuario" name="<%=AuthKeys.USERNAME_PARAMETER%>"
                   type="text" pattern="<%=AuthKeys.USERNAME_REGEX%>" required>
            <label for="username">Usuario</label>
        </div>
        <div class="form-floating col-12">
            <input id="password" class="form-control" placeholder="Contraseña" name="<%=AuthKeys.PASSWORD_PARAMETER%>"
                   type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>" required>
            <label for="password">Contraseña</label>
        </div>
        <div class="form-floating col-12">
            <input id="repeat_password" class="form-control" placeholder="Repite tu contraseña"
                   name="<%=AuthKeys.REPEAT_PASSWORD_PARAMETER%>" type="password" pattern="<%=AuthKeys.PASSWORD_REGEX%>"
                   required>
            <label for="repeat_password">Repite tu contraseña</label>
        </div>
        <div class="form-floating col-12">
            <input id="first_name" class="form-control" placeholder="Nombre" name="<%=AuthKeys.NAME_PARAMETER%>"
                   type="text" required>
            <label for="first_name">Nombre</label>
        </div>
        <div class="form-floating col-12">
            <input id="last_name" class="form-control" placeholder="Apellidos" name="<%=AuthKeys.LAST_NAME_PARAMETER%>"
                   type="text" required>
            <label for="last_name">Apellidos</label>
        </div>
        <div class="col-md-6">
            <label for="address" class="form-label">Dirección</label>
            <input id="address" class="form-control" name="<%=AuthKeys.ADDRESS_PARAMETER%>" type="text" required>
        </div>
        <div class="col-md-6">
            <label for="city" class="form-label">Ciudad</label>
            <input id="city" class="form-control" name="<%=AuthKeys.CITY_PARAMETER%>" type="text" required>
        </div>
        <div class="col-md-6">
            <label for="birth" class="form-label">Fecha de nacimiento</label>
            <input id="birth" class="form-control" name="<%=AuthKeys.BIRTH_PARAMETER%>" type="date" required>
        </div>
        <div class="col-md-6">
            <label for="gender" class="form-label">Género</label>
            <select id="gender" class="form-select" name="<%=AuthKeys.GENDER_PARAMETER%>" required>
                <% for (GenderEnum gender : GenderEnum.values()) {
                    String genero = "";
                    if (gender.toString().equals("male")) {
                        genero = "Masculino";
                    } else if (gender.toString().equals("female")) {
                        genero = "Femenino";
                    } else {
                        genero = "Otro";
                    }
                %>

                <option><%=genero%>
                </option>
                <%}%>
            </select>
        </div>
        <div class="row-1">
            <button type="submit" class="btn btn-primary col-6">Crear cuenta</button>
        </div>
    </form>
    <small>
        <a class="link-primary" href="${pageContext.request.contextPath}/auth/login">
            ¿Tiene ya una cuenta en Ubay?
        </a>
    </small>
</main>
</body>
</html>
