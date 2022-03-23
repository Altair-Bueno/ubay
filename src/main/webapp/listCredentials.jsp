<%@ page import="java.util.List" %>
<%@ page import="uma.taw.ubay.entity.LoginCredentialsEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 18/3/22
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<LoginCredentialsEntity> list = (List<LoginCredentialsEntity>) request.getAttribute("list-credentials");
%>
<html>
<head>
    <title>List</title>
</head>
<body>
    <h1>Found <%=list.size()%> users</h1>
    <table>
        <tr>
            <th>Username</th>
            <th>Password</th>
        </tr>
        <%for (LoginCredentialsEntity entity : list){%>
            <tr>
                <td><%=entity.getUsername()%></td>
                <td><%=entity.getPassword()%></td>
            </tr>
        <%}%>
    </table>
</body>
</html>
