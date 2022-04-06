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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Modify user</title>
</head>
<body>
    <a class="btn btn-primary m-2" href="../users/" role="button">Go back</a>

    <div class="container">
        <h2>Data</h2>

        <div class="row">
            <div class="col-3">
                <form action="modify" method="get">
                    <div class="form col">
                        ID: <input type="text" class="form-control" name="id" value="<%=request.getParameter("id")%>"> <br>
                        Name: <input type="text" class="form-control" name="name" value="<%=request.getParameter("name")%>"> <br>
                        Last name: <input type="text" class="form-control" name="lastName" value="<%=request.getParameter("lastName")%>"> <br>
                        Address: <input type="text" class="form-control" name="address" value="<%=request.getParameter("address")%>"> <br>
                        City: <input type="text" class="form-control" name="city" value="<%=request.getParameter("city")%>"> <br>
                        Gender: <select name="gender" class="form-select">
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
                    </select> </br>
                        Birth date: <input type="date" class="form-control" name="birthDate" value="<%=request.getParameter("birthDate")%>"> <br>
                        <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    </div>
                </form>
            </div>
    </div>

</body>
</html>
