<%@ page import="uma.taw.ubay.AuthKeys" %>
<%@ page import="uma.taw.ubay.entity.GenderEnum" %><%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 30/3/22
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify user</title>
    <a href="${pageContext.request.contextPath}/admin/users" >Go back</a>
    <h1>Data: </h1>

    <form action="${pageContext.request.contextPath}/admin/modifyUser" method="get">
        <label>
            ID: <input type="text" name="id" value="<%=request.getParameter("id")%>"> <br>
            Name: <input type="text" name="name" value="<%=request.getParameter("name")%>"> <br>
            Last name: <input type="text" name="lastName" value="<%=request.getParameter("lastName")%>"> <br>
            Gender: <select name="gender">
                <% for (GenderEnum gender : GenderEnum.values()){
                    String clientGender = request.getParameter("gender");
                    GenderEnum clientGenderEnum = GenderEnum.valueOf(clientGender);
                    if(gender.equals(clientGenderEnum)){
                %>
                    <option selected><%=gender.toString()%></option>
                <%
                    } else {
                %>
                    <option><%=gender.toString()%></option>
                <%
                    }
                %>
                <%}%>
            </select> <br>
            Address: <input type="text" name="address" value="<%=request.getParameter("address")%>"> <br>
            City: <input type="text" name="city" value="<%=request.getParameter("city")%>"> <br>
            Birth date: <input type="date" name="birthDate" value="<%=request.getParameter("birthDate")%>"> <br>
        </label>

        <input type="submit">
    </form>
</head>
<body>

</body>
</html>
