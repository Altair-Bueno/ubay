<%@ page import="uma.taw.ubay.SessionKeys" %>
<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="uma.taw.ubay.entity.KindEnum" %>
<%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 30/3/22
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- JavaScript Bundle with Popper -->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous">
</script>

<%
    Object navsesion = session.getAttribute(SessionKeys.LOGIN_CREDENTIALS);
    String username = navsesion == null ? "Usuario nuevo" : ((LoginCredentialsEntity) navsesion).getUsername();
    Object currentURLObject = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
    String currenturl = currentURLObject == null ? request.getRequestURI() : currentURLObject.toString();
    if(currenturl.charAt(currenturl.length()-1) =='/') currenturl = currenturl.substring(0, currenturl.length()-1);
    Map<String, String> urls = new LinkedHashMap<>();
    urls.put("Inicio", request.getContextPath());
    urls.put("Productos", request.getContextPath() + "/product");

    if (navsesion == null) {

    } else if (((LoginCredentialsEntity) navsesion).getKind().equals(KindEnum.client)) {
        urls.put("Favoritos",request.getContextPath() + "/users/products");
        urls.put("Pujas realizadas", request.getContextPath() + "/users/bids");
        urls.put("Pujas recibidas",request.getContextPath() + "/vendor/bids");
    } else if (((LoginCredentialsEntity) navsesion).getKind().equals(KindEnum.admin)) {
        // TODO navbar should take care of all redirects. Admin links go here
        urls.put("Administrar", request.getContextPath() + "/admin");
    }
    urls.put("Categorias", request.getContextPath() + "/categories");
%>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Ubay</a>
        <ul class="navbar-nav me-auto mb-2 mb-sm-0">
            <%
                for(String url : urls.keySet()){
            %>
            <li class="nav-item">
                <a class="nav-link <%=urls.get(url).equals(currenturl) ? "active" : ""%>" aria-current="page" href="<%=urls.get(url)%>"><%=url%></a>
            </li>
            <%
                }
            %>
        </ul>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ms-auto me-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <%=username%>
                    </a>
                    <%
                        if(navsesion != null){
                    %>
                    <ul class="dropdown-menu dropdown-menu-end mt-2" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><form method="post" action="${pageContext.request.contextPath}/auth/signoff">
                                <input type="submit" class="dropdown-item" value="Cerrar sesión">
                        </form></li>
                    </ul>
                    <%
                        } else {
                    %>
                    <ul class="dropdown-menu dropdown-menu-end mt-2" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/auth/login">Iniciar sesión</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/auth/register">Registrarse</a></li>
                    </ul>
                    <%
                        }
                    %>
                </li>
            </ul>
        </div>
    </div>
</nav>



