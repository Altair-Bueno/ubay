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
    <a href="../">Go home</a>

    <h2>Search users: </h2>
    <form>
        Search user by:
        <label for="filterBy">
            <select id="filterBy" name="filterBy">
                <option>--</option>
                <option>ID</option>
                <option>Name</option>
                <option>Address</option>
                <option>Gender</option>
                <option>City</option>
            </select>
        </label>
        <label for="Search user"></label><input type="text" name="search" id="Search user"/>
        <input type="submit"/>
    </form>

    <table border="1" id="userDataTable">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Address</th>
            <th>City</th>
            <th>Birth Date</th>
        </tr>

    <%
        List<ClientEntity> searchClient = (List)request.getAttribute("search-user");
        if(searchClient != null){
            for(ClientEntity c : searchClient){

    %>
    <tr>
        <td><%=c.getId()%></td>
        <td><%=c.getName()%></td>
        <td><%=c.getLastName()%></td>
        <td><%=c.getGender()%></td>
        <td><%=c.getAddress()%></td>
        <td><%=c.getCity()%></td>
        <td><%=c.getBirthDate()%></td>
    </tr>
    <%
            }
        }
    %>
    </table>

    &nbsp;
    &nbsp;

    <form>
        <label for="userID">ID:</label><input type="text" name="userID" id="userID">
        <label for="userName">Name:</label><input type="text" name="userName" id="userName">
        <label for="userLastName">Last Name:</label><input type="text" name="userLastName" id="userLastName"><br>
        <label for="userGender">Gender:</label><input type="text" name="userGender" id="userGender">
        <label for="userAddress">Address:</label><input type="text" name="userAddress" id="userAddress">
        <label for="userCity">City:</label><input type="text" name="userCity" id="userCity">
        <label for="userBirthDate">Birth date:</label><input type="text" name="userBirthDate" id="userBirthDate"><br>

        <p>
            <input type="radio" name="delete"> Delete selected user <br>
            <input type="radio" name="update"> Update selected user data
        </p>

        <p>
            <input type="submit" value="Submit changes">
        </p>

    </form>

    <script>
        var table = document.getElementById('userDataTable'), rIndex;

        for(var i = 0; i < table.rows.length; i++){
            table.rows[i].onclick = function(){
                rIndex = this.rowsIndex;
                document.getElementById("userID").value = this.cells[0].innerHTML;
                document.getElementById("userName").value = this.cells[1].innerHTML;
                document.getElementById("userLastName").value = this.cells[2].innerHTML;
                document.getElementById("userGender").value = this.cells[3].innerHTML;
                document.getElementById("userAddress").value = this.cells[4].innerHTML;
                document.getElementById("userCity").value = this.cells[5].innerHTML;
                document.getElementById("userBirthDate").value = this.cells[6].innerHTML;
            };
        }
    </script>

<%--
<h2>Create new user: </h2>
    <a href="../auth/register.jsp">Click here to register a new user</a>

    <h2>Delete a existing user by ID: </h2>
        <form>
            <label>
                <input name="IDdeleteUser">
                <input type="submit">
            </label>
        </form>

--%>

</div>
</body>
</html>
