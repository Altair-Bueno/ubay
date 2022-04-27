<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay-Reset my password</title>
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
          action="${pageContext.request.contextPath}/auth/resetPassword">
        <h1 class="h3 mb-3 fw-normal">Reset my password</h1>
        <div class="form-floating">
            <input
                    type="password"
                    class="form-control"
                    id="floatingPassword"
                    placeholder="New Password"
                    name="<%=AuthKeys.PASSWORD_PARAMETER%>"
                    pattern="<%=AuthKeys.PASSWORD_REGEX%>" required
            >
            <label for="floatingPassword">New Password</label>
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
            <label for="floatingRepeat">Repeat New Password</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Change
            password
        </button>
        <!-- TODO replace hidden field with query parameter-->
        <input type="text"
               class="visually-hidden"
               name="<%=AuthKeys.USERNAME_PARAMETER%>"
               value="<%=request.getParameter(AuthKeys.USERNAME_PARAMETER)%>"
        >
        <input type="text"
               class="visually-hidden"
               name="<%=AuthKeys.PASSWORD_CHANGE_ID_PARAMETER%>"
               value="<%=request.getParameter(AuthKeys.PASSWORD_CHANGE_ID_PARAMETER)%>"
        >
    </form>
</main>
</body>
</html>