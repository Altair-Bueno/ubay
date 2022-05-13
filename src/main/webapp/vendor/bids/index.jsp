<%@ page import="uma.taw.ubay.VendorKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.bids.ReceivedBidsDTO" %>
<%@ page import="uma.taw.ubay.dto.bids.ProductDTO" %>
<%--
  Created by IntelliJ IDEA.
  Author: Altair Bueno 90% Francisco Javier Hernández 10%
  Date: 5/4/22
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ReceivedBidsDTO> bidsList = (List<ReceivedBidsDTO>) request.getAttribute(VendorKeys.BID_LIST);
    String startDate = request.getParameter(VendorKeys.BID_START_DATE_PARAMETER);
    String endDate = request.getParameter(VendorKeys.BID_END_DATE_PARAMETER);
    String productTitle = request.getParameter(VendorKeys.BID_PRODUCT_TITLE_PARAMETER);
    String clientName = request.getParameter(VendorKeys.BID_CLIENT_NAME_PARAMETER);
    String pageNumberParameter = request.getParameter(VendorKeys.BID_PAGE_NUMBER_PARAMETER);

    startDate = startDate == null ? "" : startDate;
    endDate = endDate == null ? "" : endDate;
    productTitle = productTitle == null ? "" : productTitle;
    clientName = clientName == null ? "" : clientName;
    int pageNumber = 0;
    try {
        pageNumber = Integer.parseInt(pageNumberParameter);
    } catch (Exception ignored) {
    }
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay | Bids Received</title>
</head>
<body>
<%@include file="../../WEB-INF/components/navbar.jsp" %>

<div class="container mt-4">
    <div class="row">
        <h1>Pujas recibidas</h1>
    </div>
    <div class="row">

        <aside class="col-md-12 col-lg-2">
            <form action="${pageContext.request.contextPath}/vendor/bids">
                <div class="mb-3">
                    <label for="startDate" class="form-label">Fecha de publicación</label>
                    <input type="date" class="form-control" id="startDate"
                           name="<%=VendorKeys.BID_START_DATE_PARAMETER%>"
                           value="<%=startDate%>">
                </div>
                <div class="mb-3">
                    <label for="endDate" class="form-label">Fecha de cierre</label>
                    <input type="date" class="form-control" id="endDate"
                           name="<%=VendorKeys.BID_END_DATE_PARAMETER%>"
                           value="<%=endDate%>">
                </div>
                <div class="mb-3">
                    <label for="productTitle" class="form-label">Título del producto</label>
                    <input type="text" class="form-control" id="productTitle"
                           name="<%=VendorKeys.BID_PRODUCT_TITLE_PARAMETER%>"
                           value="<%=productTitle%>">
                </div>
                <div class="mb-3">
                    <label for="clientName" class="form-label">Nombre del cliente</label>
                    <input type="text" class="form-control" id="clientName"
                           name="<%=VendorKeys.BID_CLIENT_NAME_PARAMETER%>"
                           value="<%=clientName%>">
                </div>
                <div class="mb-3">
                    <label for="pageNumber" class="form-label">Página</label>
                    <input type="number" class="form-control" id="pageNumber"
                           name="<%=VendorKeys.BID_PAGE_NUMBER_PARAMETER%>"
                           value="<%=pageNumber%>">
                </div>
                <select
                        class="form-select mb-3"
                        id="orderBy"
                        name="<%=VendorKeys.ORDER_BY_PARAMETER%>"
                >
                    <%for (String orderBy : VendorKeys.ORDER_BY_LIST) {%>
                    <option <%=orderBy.equals(request.getParameter(VendorKeys.ORDER_BY_PARAMETER)) ? "selected" : ""%>
                            value="<%=orderBy%>">
                        <%=orderBy%>
                    </option>
                    <%}%>
                </select>
                <div class="form-check mb-3">
                    <input
                            class="form-check-input"
                            type="checkbox"
                            id="flexCheckDefault"
                            name="<%=VendorKeys.ASC_PARAMETER%>"
                        <%=request.getParameter(VendorKeys.ASC_PARAMETER) == null ? "":"checked"%>
                    >
                    <label class="form-check-label" for="flexCheckDefault">
                        Ordenar ascendentemente
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">Filtrar</button>
                <button type="button" class="btn btn-secondary" onclick="clearFilter()">Limpiar</button>
            </form>
        </aside>
        <main class="table-responsive col">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fecha de publicación</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Producto</th>
                    <th scope="col">Nombre del cliente</th>
                </tr>
                </thead>
                <tbody>
                <%for (int i = 0; i < bidsList.size(); i++) {%>
                <tr>
                    <%
                        ReceivedBidsDTO bid = bidsList.get(i);
                        ProductDTO product = bid.getProduct();
                    %>
                    <th scope="row">
                        <%=1 + i + pageNumber * 10%>
                    </th>
                    <td>
                        <%=bid.getPublishDate()%>
                    </td>
                    <td>
                        $<%=bid.getAmount()%>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/item?id=<%=product.getId()%>">
                            <%=product.getTitle()%>
                        </a>
                    </td>
                    <td>
                        <%=bid.getUser().getName()%>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </main>
    </div>
</div>

<script>
    function clearFilter() {
        document.querySelector("#startDate").value = ""
        document.querySelector("#endDate").value = ""
        document.querySelector("#productTitle").value = ""
        document.querySelector("#clientName").value = ""
        document.querySelector("#pageNumber").value = "0"
        document.querySelector("#orderBy").selectedIndex = 0
    }
</script>

</body>
</html>
