<%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 8/4/22
  Time: 03:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Error: <%=exception.getMessage()%>
    </title>
</head>
<%
    String referer = request.getHeader("Referer");
%>
<body>
<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center text-danger position-absolute top-50 start-50 translate-middle">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block"><%=exception.getMessage()%></span>
                <a href="<%=referer == null ? request.getContextPath() : referer%>" class="btn btn-link">Go back</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>