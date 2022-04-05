<%@ page import="java.util.stream.Stream" %>
<%@ page import="uma.taw.ubay.entity.BidEntity" %>
<%@ page import="uma.taw.ubay.VendorKeys" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 5/4/22
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BidEntity> bidsList = (List<BidEntity>) request.getAttribute(VendorKeys.BIDS_BY_VENDOR);
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Ubay-Bids Recived</title>
</head>
<body>
    <div class="container">
        <h1>Bids recived</h1>
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
            <%for (int i = 0;i<bidsList.size();i++) {%>
            <tr>
                <%BidEntity bid = bidsList.get(i);%>
                <th scope="row"><%=i%></th>
                    <td><%=bid.getPublishDate()%></td>
                    <td>$<%=bid.getAmount()%></td>
                    <%--TODO replace placeholder with actual link--%>
                    <td>LINK PLACEHOLDER<%=bid.getProduct()%></td>
                    <td><%=bid.getUser().getName()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</body>
</html>
