<%--
  Created by IntelliJ IDEA.
  User: jota
  Date: 5/4/22
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Modify category</title>
</head>
<body>
<%@include file="../WEB-INF/components/navbar.jsp"%>
<a class="btn btn-primary m-2" href="../categories" role="button">Go back</a>
    <div class="container">
        <h2>Data: </h2>
        <div class="row">
            <div class="col-3">
                <form action="modify" method="get">
                    <div class="form col">
                        <label>
                            ID: <input type="text" class="form-control" name="id" value="<%=request.getParameter("id")%>"> <br>
                            Name: <input type="text" class="form-control" name="name" value="<%=request.getParameter("name")%>"> <br>
                            Description: <input type="text" class="form-control" name="description" value="<%=request.getParameter("description")%>"> <br>
                            <button type="submit" class="btn btn-primary mt-2">Submit</button>
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>



</body>
</html>
