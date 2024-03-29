<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="uma.taw.ubay.dto.products.ProductDTO" %>
<%@ page import="uma.taw.ubay.dto.products.ProductClientDTO" %>
<%@ page import="uma.taw.ubay.UsersKeys" %>
<%@ page import="uma.taw.ubay.dto.products.ProductBidDTO" %>
<%@ page import="uma.taw.ubay.servlet.product.Product" %>
<%--
Created by IntelliJ IDEA.
  Author: Francisco Javier Hernández
  Date: 6/4/22
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <!-- Bootstrap icons -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"
    />


    <title>Ubay | Producto</title>
</head>
<body>
<%
    Object userParameter = request.getAttribute("user");
    ProductDTO p = (ProductDTO) request.getAttribute("product");
    Object isFavParameter = request.getAttribute("isFav");
    Object highestBidParameter = request.getAttribute("highestBid");
    Object isAdminParameter = request.getAttribute("isAdmin");
    boolean isAdmin = isAdminParameter != null && (boolean) isAdminParameter;
    double minBid;
    boolean cerrado = p.getCloseDate() != null;
    String imgSrc = p.getImages() == null ? "" : request.getContextPath() + "/image?id=" + URLEncoder.encode(p.getImages(), StandardCharsets.UTF_8);
%>

<script>document.title = "Ubay | <%= p.getTitle()%>"</script>
<%@include file="../WEB-INF/components/navbar.jsp" %>

<div class="d-flex flex-column">
    <div class="d-flex flex-row m-auto p-2">
        <div class="p-2"><img src="<%=imgSrc%>" class="rounded" alt="<%=p.getTitle()%>"
                              style="height: 500px; width: 500px;"></div>
        <div class="d-flex flex-column p-2">
            <div class="p-2"><h1><%=p.getTitle()%>
            </h1></div>
            <div class="p-2"><h1><%=p.getOutPrice()%> €</h1></div>
            <div class="p-2">
                <h2>Estado: </h2>
                <h4><%= !cerrado ? "Activo" : "Cerrado"%>
                </h4>
            </div>
            <div class="p-2">
                <h2>Descripcion: </h2>
                <h6><%=p.getDescription()%>
                </h6>
            </div>
            <%
                if (userParameter != null || isAdmin) {
                    if (isAdmin || (userParameter != null && ((ProductClientDTO) userParameter).equals(p.getVendor()))) {
            %>

            <div class="d-flex flex-row">

            <%
                if(!cerrado){

            %>
                <!-- EDITAR -->
                <form method="get" action="${pageContext.request.contextPath}/product/update">
                    <input type='hidden' name='id' value="<%=p.getId()%>"/>
                    <input class="btn btn-secondary btn-block me-2" type="submit" value="Editar">
                </form>
            <%
                }
            %>


                <!-- BORRAR: Button trigger modal -->
                <button type="button" class="btn btn-danger btn-block" data-bs-toggle="modal"
                        data-bs-target="#deleteModal">
                    Eliminar
                </button>

                <%
                    if(!cerrado){

                %>
                <button type="button" class="btn btn-warning" data-bs-toggle="modal"
                        data-bs-target="#closeProductModal">
                    Cerrar
                </button>
                <%
                    }
                %>
            </div>

            <!-- Delete Modal -->
            <div class="modal fade" id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Eliminar producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            ¿Está seguro de que quiere eliminar el producto?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <form method="get" action="${pageContext.request.contextPath}/product/delete">
                                <input type='hidden' name='id' value="<%=p.getId()%>"/>
                                <input class="btn btn-danger" type="submit" value="Eliminar">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Close Product Modal -->
            <div class="modal fade" id="closeProductModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel2">Eliminar producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            ¿Está seguro de que quiere cerrar el producto?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <form method="post" action="${pageContext.request.contextPath}/product/update" enctype="multipart/form-data">
                                <input type='hidden' name='id' value="<%=p.getId()%>"/>
                                <input type='hidden' name='categoria' value="<%=p.getCategory().getId()%>"/>
                                <input type='hidden' name='estado' value="Cerrado"/>
                                <input type='hidden' name='descripcion' value="<%=p.getDescription()%>"/>
                                <input type='hidden' name='titulo' value="<%=p.getTitle()%>"/>
                                <input type='hidden' name='precio' value="<%=p.getOutPrice()%>"/>
                                <input class="btn btn-warning" type="submit" value="Cerrar">
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <%
            } else {
                if (!cerrado) {
                    if (highestBidParameter == null) {
                        minBid = p.getOutPrice();
            %>
            <div class="row">
                <h2>Este producto no ha recibido todavía ninguna puja</h2>
                <h2>Precio de puja mínima: <%=p.getOutPrice()%>
                </h2>
            </div>
            <%
            } else {
                ProductBidDTO highestBid = (ProductBidDTO) highestBidParameter;
                minBid = highestBid.getAmount();
            %>
            <div class="row">
                <h2>Puja más alta actual: <%=highestBid.getAmount()%>
                </h2>
            </div>
            <%
                }
            %>

            <div class="row align-items-center p-2">
                <form method="post" action="${pageContext.request.contextPath}/users/bids/new">
                    <div class="col-auto w-25">
                        <input type="number" min="<%=minBid%>" step="0.01" name="<%=UsersKeys.BID_AMOUNT_PARAMETER%>"
                               class="form-control" placeholder="Cantidad a pujar..." required>
                    </div>
                    <div class="col-auto">
                        <input type='hidden' name="<%=UsersKeys.BID_PRODUCT_ID_PARAMETER%>" id='id-compra'
                               value="<%=p.getId()%>"/>
                        <input class="btn btn-primary" type="submit" value="Pujar"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="p-4">
            <%
                }
                if (userParameter != null) {
                    ProductClientDTO user = (ProductClientDTO) userParameter;
                    if(isFavParameter != null && (boolean) isFavParameter){
            %>

            <div class="d-flex flex-row">
                <form method="get" action="${pageContext.request.contextPath}/users/deleteFavourite">
                    <input type='hidden' name='productID' value="<%=p.getId()%>"/>
                    <input type='hidden' name='clientID' value="<%=user.getId()%>"/>
                    <button class="btn btn btn-outline-danger btn-labeled" type="submit">
                        <span><i class="bi bi-star-fill"></i></span>Eliminar de favoritos
                    </button>
                </form>
            </div>

            <%

                } else {
            %>

            <form method="get" action="${pageContext.request.contextPath}/users/addFavourite">
                <input type='hidden' name='productID' value="<%=p.getId()%>"/>
                <input type='hidden' name='clientID' value="<%=user.getId()%>"/>
                <button class="btn btn btn-outline-warning btn-labeled" type="submit">
                    <span><i class="bi bi-star-fill"></i></span>Añadir a favoritos
                </button>
            </form>

            <%
                            }
                        }
                    }
                }
            %>
        </div>
    </div>

</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>
