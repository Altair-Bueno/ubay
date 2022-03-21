<%@ page import="uma.taw.ubay.entity.ExampleEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: compux72
  Date: 18/3/22
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ExampleEntity> list = (List<ExampleEntity>) request.getAttribute("example");
%>
<html>
<head>
    <title>List</title>
</head>
<body>
    <h1>Found <%=list.size()%> numbers</h1>
    <%for (ExampleEntity entity : list){%>
        <h2>number: <%=entity.getNumber()%></h2>
    <%}%>
</body>
</html>
