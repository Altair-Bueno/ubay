<%@ page import="java.util.*" %>
<%@ page import="uma.taw.ubay.dto.notifications.BidsDTO" %>
<%@ page import="uma.taw.ubay.dto.notifications.ProductDTO" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%--
  Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 24/4/22
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Notificaciones</title>
</head>
<body>
    <%@include file="../WEB-INF/components/navbar.jsp"%>
    <%
        HashMap<BidsDTO, Boolean> notificaciones = (HashMap<BidsDTO, Boolean>) request.getAttribute("notifications");

        if(notificaciones.size() == 0){
    %>
    <div class="m-auto w-50 h-75 d-flex justify-content-center align-items-center">
        <h1>Tiene 0 notificaciones.</h1>
    </div>

    <%
    } else {
    %>

    <table class="table table-bordered text-center">
        <thead>
            <tr>
                <th scope="col">Imagen</th>
                <th scope="col">Titulo</th>
                <th scope="col">Fecha de cierre</th>
                <th scope="col">Resultado</th>
            </tr>
        </thead>
        <tbody>
        <%
            for(BidsDTO b : notificaciones.keySet()){
                ProductDTO p = b.getProduct();
                String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
        %>

            <tr onclick="window.location='${pageContext.request.contextPath}/product/item?id=' + <%=p.getId()%>">
                <td><img src="<%=imgSrc%>" class="img-thumbnail" alt="<%=p.getTitle()%>" style="width: 200px"></td>
                <td class="align-middle"><h3><%=p.getTitle()%></h3></td>
                <td class="align-middle"><%=p.getCloseDate()%></td>
                <td class="align-middle"><%=notificaciones.get(b) ? "Ganada" : "Perdida"%></td>
            </tr>

        <%
            }
        %>
        </tbody>



    </table>
    <%
        }
    %>

</body>
</html>
