<%@ page import="uma.taw.ubay.entity.BidEntity" %>
<%@ page import="uma.taw.ubay.VendorKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.entity.ProductEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 5/4/22
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BidEntity> bidsList = (List<BidEntity>) request.getAttribute(VendorKeys.BID_LIST);
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
    <title>Ubay-Bids Received</title>
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <h1>Bids received</h1>
    </div>
    <div class="row">

        <aside class="col-md-12 col-lg-2">
            <form action="${pageContext.request.contextPath}/vendor/bids">
                <div class="mb-3">
                    <label for="startDate" class="form-label">Start Date</label>
                    <input type="date" class="form-control" id="startDate"
                           name="<%=VendorKeys.BID_START_DATE_PARAMETER%>"
                           value="<%=startDate%>">
                </div>
                <div class="mb-3">
                    <label for="endDate" class="form-label">End Date</label>
                    <input type="date" class="form-control" id="endDate"
                           name="<%=VendorKeys.BID_END_DATE_PARAMETER%>"
                           value="<%=endDate%>">
                </div>
                <div class="mb-3">
                    <label for="productTitle" class="form-label">Product
                        Title</label>
                    <input type="text" class="form-control" id="productTitle"
                           name="<%=VendorKeys.BID_PRODUCT_TITLE_PARAMETER%>"
                           value="<%=productTitle%>">
                </div>
                <div class="mb-3">
                    <label for="clientName" class="form-label">Client
                        name</label>
                    <input type="text" class="form-control" id="clientName"
                           name="<%=VendorKeys.BID_CLIENT_NAME_PARAMETER%>"
                           value="<%=clientName%>">
                </div>
                <div class="mb-3">
                    <label for="pageNumber" class="form-label">Page</label>
                    <input type="number" class = "form-control" id="pageNumber"
                           name="<%=VendorKeys.BID_PAGE_NUMBER_PARAMETER%>"
                           value="<%=pageNumber%>">
                </div>
                <button type="submit" class="btn btn-primary">Filter</button>
            </form>
        </aside>
        <main class="table-responsive col">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Published on</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Product</th>
                    <th scope="col">Client name</th>
                </tr>
                </thead>
                <tbody>
                <%for (int i = 0; i < bidsList.size(); i++) {%>
                <tr>
                    <%
                        BidEntity bid = bidsList.get(i);
                        ProductEntity product = bid.getProduct();
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
</body>
</html>
