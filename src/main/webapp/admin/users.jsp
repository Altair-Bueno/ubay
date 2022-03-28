<%@ page import="uma.taw.ubay.entity.ClientEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 28/3/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <h3>System users table: </h3>

    <table>
        <tr>
            <th>Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Address</th>
            <th>City</th>
            <th>Birth Date</th>
        </tr>
    <%
        List<ClientEntity> clientList = (List)request.getAttribute("users-list");

        for(ClientEntity c : clientList){
    %>
        <tr>
            <td><%=c.getName()%></td>
            <td><%=c.getLastName()%></td>
            <td><%=c.getGender()%></td>
            <td><%=c.getAddress()%></td>
            <td><%=c.getCity()%></td>
            <td><%=c.getBirthDate()%></td>
        </tr>
    <%
        }
    %>

    </table>
    <form>
        <label for="Search user"></label>
        <input type="text" name="search" id="Search user"/>
        <input type="submit"/>
    </form>

    <table>
        <tr>
            <th>Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Address</th>
            <th>City</th>
            <th>Birth Date</th>
        </tr>

    <%
        List<ClientEntity> searchClient = (List)request.getAttribute("search-user");

        for(ClientEntity c : searchClient){
    %>
    <tr>
        <td><%=c.getName()%></td>
        <td><%=c.getLastName()%></td>
        <td><%=c.getGender()%></td>
        <td><%=c.getAddress()%></td>
        <td><%=c.getCity()%></td>
        <td><%=c.getBirthDate()%></td>
    </tr>
    <%
        }
    %>

    </table>
</div>
</body>
</html>
