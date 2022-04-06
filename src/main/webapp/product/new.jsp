<%--
  Created by IntelliJ IDEA.
  User: franm
  Date: 28/3/22
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Producto</title>
</head>
<body>

    <%
        HttpServletRequest req = request;
        String id = req.getParameter("id");
    %>

    <h1>Titulo:
        <%=id%>
    </h1>




</body>
</html>
