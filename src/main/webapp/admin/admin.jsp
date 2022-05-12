<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 8/4/22
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Admin main page</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp"%>
<div class="container">
    <div class="col position-absolute justify-content-center top-50 start-50 translate-middle">
        <h2 class="p-2">Hi, admin.</h2>
        <a class="btn btn-primary m-2" href="../users/" role="button">Manage users</a>
        <a class="btn btn-primary m-2" href="../categories" role="button">Manage categories</a>
        <a class="btn btn-primary m-2" href="../product/productslist" role="button">View all Ubay products</a>
        <form method="post" action="../auth/signoff">
            <input type="submit" class="btn btn-danger m-2" value="Sign off">
        </form>
    </div>
</div>

</body>
</html>
