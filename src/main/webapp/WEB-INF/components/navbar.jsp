<%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 30/3/22
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Ubay</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/product">Productos</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/users/bids">Pujas</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/vendor/bids">Mis Pujas</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/users/products">Productos Favoritos</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/categories/">Categorias Favoritas</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/signoff">Cerrar sesión</a>

            </div>
        </div>
    </div>
</nav>

