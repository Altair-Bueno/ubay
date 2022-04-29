<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.users.ProductDTO" %><%--
  Created by IntelliJ IDEA.
  User: franm
  Date: 24/4/22
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay | Notificaciones</title>
</head>
<body>

    <%
        List<ProductDTO> notificaciones = (List<ProductDTO>) request.getAttribute("notifications");

        if(notificaciones.size() == 0){
    %>
    <h1>0 notificaciones.</h1>
    <%
    } else {
        for(ProductDTO p : notificaciones){
    %>
    <h1><%=p.getTitle()%>: puja cerrada.</h1>

    <%
            }
        }
    %>

</body>
</html>
