<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 11/4/22
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Delete Favourite</title>
</head>
<body>
<div class="container">
    <%
        String id = request.getParameter("clientID");
    %>

    <div class="col-6 position-absolute top-50 start-50 translate-middle">
        <form class="form" action="../users/products?id=<%=id%>" method="get">
            <h1>Are you sure you want to delete favourite product?</h1>
            <button type="submit" class="btn btn-danger mt-2">Submit</button>
        </form>
    </div>


</div>
</body>
</html>
