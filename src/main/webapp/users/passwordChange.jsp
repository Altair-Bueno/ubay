<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %><%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 21/4/22
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Cambiar contrasena</title>
</head>
<body>
<%
    String passwordChangeID = (String) request.getAttribute("passwordChangeID");
    String usernamepwc = (String) request.getAttribute("username");
%>
<%@include file="../WEB-INF/components/navbar.jsp" %>

<div class="col-6 position-absolute top-50 start-50 translate-middle">
    <h1>Enlace de reseteo de contraseña:</h1>
    <input type="text" size="80" id="linkReseteo"
           value="${pageContext.request.contextPath}/auth/resetPassword?passwordChangeID=<%=URLEncoder.encode(passwordChangeID,StandardCharsets.UTF_8)%>&username=<%=URLEncoder.encode(usernamepwc,StandardCharsets.UTF_8)%>"/>
    <button class="btn btn-primary mt-2" onclick="copyToClipboard()">Copiar link al portapapeles</button>
</div>

<script>
    function copyToClipboard() {
        /* Get the text field */
        var copyText = document.getElementById("linkReseteo");

        /* Select the text field */
        copyText.select();
        copyText.setSelectionRange(0, 99999); /* For mobile devices */

        /* Copy the text inside the text field */
        navigator.clipboard.writeText(copyText.value);
    }
</script>


<%--    <a href="${pageContext.request.contextPath}/auth/resetPassword?passwordChangeID=<%=URLEncoder.encode(passwordChangeID,StandardCharsets.UTF_8)%>&username=<%=URLEncoder.encode(username,StandardCharsets.UTF_8)%>">This is the link</a>--%>
</body>
</html>
