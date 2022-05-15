<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Altair Bueno
  Date: 28/3/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Login</title>
</head>
<style>
    html,
    body {
        height: 100%;
    }

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

    .form-signin .checkbox {
        font-weight: 400;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body class="text-center">
<main class="form-signin">
    <form method="post" action="${pageContext.request.contextPath}/auth/login">
        <%--        <img class="mb-4" src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">--%>
        <h1 class="h3 mb-3 fw-normal">Por favor, inicie sesión</h1>
        <div class="form-floating">
            <input
                    type="text"
                    class="form-control"
                    id="floatingInput"
                    placeholder="Username"
                    name="<%=AuthKeys.USERNAME_PARAMETER%>"
                    pattern="<%=AuthKeys.USERNAME_REGEX%>" required
            >
            <label for="floatingInput">Usuario</label>
        </div>
        <div class="form-floating">
            <input
                    type="password"
                    class="form-control"
                    id="floatingPassword"
                    placeholder="Password"
                    name="<%=AuthKeys.PASSWORD_PARAMETER%>"
                    pattern="<%=AuthKeys.PASSWORD_REGEX%>" required
            >
            <label for="floatingPassword">Contraseña</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Iniciar sesión</button>
    </form>
    <small>
        <a class="link-primary" href="${pageContext.request.contextPath}/auth/register">
            Crear una cuenta
        </a>
    </small>
</main>
</body>
</html>