<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="uma.taw.ubay.entity.KindEnum" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>JSP - Hello World</title>
</head>
<body>
<%
    LoginCredentialsEntity entity = (LoginCredentialsEntity) session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
    if (entity != null && entity.getKind().equals(KindEnum.client)) {
%>
    <%@include file="WEB-INF/components/navbar.jsp"%>
    <div class="mx-auto mt-4 w-50 d-flex flex-column align-items-center">
        <h1 class="display-1 p-2">Bienvenido a Ubay</h1>
        <h1 class="display-4 p-2"><%=entity.getUsername()%></h1>
        <button class="btn btn-primary p-2" onclick="window.location='${pageContext.request.contextPath}/product'">Ver lista de productos</button>
    </div>

<%
    } else if(entity!= null && entity.getKind().equals(KindEnum.admin)){
        response.sendRedirect(request.getContextPath() + "/admin/");
%>


<%
    } else {
%>
    <div class="mx-auto mt-4 w-50 d-flex flex-column align-items-center">
        <h1 class="display-1 p-2">Bienvenido a Ubay</h1>
        <div class="d-flex flex-row align-items-center p-2">
            <button class="btn btn-primary p-2 me-3" onclick="window.location='${pageContext.request.contextPath}/auth/login'">Iniciar sesión</button>
            <button class="btn btn-secondary p-2" onclick="window.location='${pageContext.request.contextPath}/auth/register'">Registrarse</button>
        </div>
        <button class="btn btn-info p-2 ms-3" onclick="window.location='${pageContext.request.contextPath}/admin/users'">Acceso de Admin</button>
    </div>
<%
    }
%>
</body>
</html>