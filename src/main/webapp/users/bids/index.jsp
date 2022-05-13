<%@ page import="uma.taw.ubay.UsersKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.dto.bids.SentBidsDTO" %>
<%@ page import="uma.taw.ubay.dto.bids.ProductDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Altair Bueno 90% Francisco Javier Hernández 10%
  Date: 7/4/22
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SentBidsDTO> bidsList = (List<SentBidsDTO>) request.getAttribute(UsersKeys.BID_LIST);
    String startDate = request.getParameter(UsersKeys.BID_START_DATE_PARAMETER);
    String endDate = request.getParameter(UsersKeys.BID_END_DATE_PARAMETER);
    String productTitle = request.getParameter(UsersKeys.BID_PRODUCT_TITLE_PARAMETER);
    String vendorName = request.getParameter(UsersKeys.BID_VENDOR_NAME_PARAMETER);
    String pageNumberParameter = request.getParameter(UsersKeys.BID_PAGE_NUMBER_PARAMETER);

    startDate = startDate == null ? "" : startDate;
    endDate = endDate == null ? "" : endDate;
    productTitle = productTitle == null ? "" : productTitle;
    vendorName = vendorName == null ? "" : vendorName;
    int pageNumber = 0;
    try {
        pageNumber = Integer.parseInt(pageNumberParameter);
    } catch (Exception ignored) {
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay-My bids</title>
</head>
<body>
<%@include file="../../WEB-INF/components/navbar.jsp" %>

<div class="container mt-4">
    <div class="row">
        <h1>Mis pujas</h1>
    </div>
    <div class="row">

        <aside class="col-md-12 col-lg-2">
            <form action="${pageContext.request.contextPath}/users/bids">
                <div class="mb-3">
                    <label for="startDate" class="form-label">Fecha de publicación</label>
                    <input type="date" class="form-control" id="startDate"
                           name="<%=UsersKeys.BID_START_DATE_PARAMETER%>"
                           value="<%=startDate%>">
                </div>
                <div class="mb-3">
                    <label for="endDate" class="form-label">Fecha de cierre</label>
                    <input type="date" class="form-control" id="endDate"
                           name="<%=UsersKeys.BID_END_DATE_PARAMETER%>"
                           value="<%=endDate%>">
                </div>
                <div class="mb-3">
                    <label for="productTitle" class="form-label">Título del producto</label>
                    <input type="text" class="form-control" id="productTitle"
                           name="<%=UsersKeys.BID_PRODUCT_TITLE_PARAMETER%>"
                           value="<%=productTitle%>">
                </div>
                <div class="mb-3">
                    <label for="clientName" class="form-label">Nombre del vendedor</label>
                    <input type="text" class="form-control" id="clientName"
                           name="<%=UsersKeys.BID_VENDOR_NAME_PARAMETER%>"
                           value="<%=vendorName%>">
                </div>
                <div class="mb-3">
                    <label for="pageNumber" class="form-label">Página</label>
                    <input type="number" class="form-control" id="pageNumber"
                           name="<%=UsersKeys.BID_PAGE_NUMBER_PARAMETER%>"
                           value="<%=pageNumber%>">
                </div>
                <select
                        class="form-select mb-3"
                        id="orderBy"
                        name="<%=UsersKeys.ORDER_BY_PARAMETER%>"
                >
                    <%for (String orderBy : UsersKeys.ORDER_BY_LIST) {%>
                    <option <%=orderBy.equals(request.getParameter(UsersKeys.ORDER_BY_PARAMETER)) ? "selected" : ""%>
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
                            name="<%=UsersKeys.ASC_PARAMETER%>"
                        <%=request.getParameter(UsersKeys.ASC_PARAMETER) == null ? "":"checked"%>
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
                    <th scope="col">Nombre del vendedor</th>
                </tr>
                </thead>
                <tbody>
                <%for (int i = 0; i < bidsList.size(); i++) {%>
                <tr>
                    <%
                        SentBidsDTO bid = bidsList.get(i);
                        ProductDTO product = bid.getProduct();
                    %>
                    <th scope="row">
                        <%=1 + i + pageNumber * 10%>
                    </th>
                    <td>
                        <%=dateFormat.format(bid.getPublishDate())%>
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
                        <%=product.getVendor().getName()%>
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
