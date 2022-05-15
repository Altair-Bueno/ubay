<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Altair Bueno
  Date: 28/3/22
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Cambiar mi contraseña</title>
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

    .form-signin .last {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body class="text-center">
<main class="form-signin">
    <form method="post"
          action="${pageContext.request.contextPath}/auth/changePassword">
        <%--        <img class="mb-4" src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">--%>
        <h1 class="h3 mb-3 fw-normal">Cambiar mi contraseña</h1>
        <div class="form-floating">
            <input type="password"
                   class="form-control"
                   id="floatingInput"
                   placeholder="Old Password"
                   name="<%=AuthKeys.OLD_PASSWORD_PARAMETER%>"
                   pattern="<%=AuthKeys.PASSWORD_REGEX%>"
                   required
            >
            <label for="floatingInput">Contraseña actual</label>
        </div>
        <div class="form-floating">
            <input
                    type="password"
                    class="form-control"
                    id="floatingPassword"
                    placeholder="New Password"
                    name="<%=AuthKeys.PASSWORD_PARAMETER%>"
                    pattern="<%=AuthKeys.PASSWORD_REGEX%>" required
                    aria-describedby="passwordHelp"
            >
            <label for="floatingPassword">Nueva contraseña</label>
            <div id="passwordHelp" class="form-text">8 caracteres mínimo</div>
        </div>
        <div class="form-floating last">
            <input
                    type="password"
                    class="form-control"
                    id="floatingRepeat"
                    placeholder="Repeat New Password"
                    name="<%=AuthKeys.REPEAT_PASSWORD_PARAMETER%>"
                    pattern="<%=AuthKeys.PASSWORD_REGEX%>" required
            >
            <label for="floatingRepeat">Repetir nueva contraseña</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Cambiar contraseña</button>
    </form>
</main>
</body>
</html>