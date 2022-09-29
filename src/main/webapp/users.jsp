<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <jsp:include page="header.jsp">
            <jsp:param name="current" value=""/>
        </jsp:include>
        <h2>User Overview</h2>
    </header>
    <main>
        <table>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>First Name</th>
                <th>Email</th>
                <th>Team</th>
                <th>Role</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <tr>
                <td>jan.janssens@hotmail.com</td>
                <td>Jan</td>
                <td>Janssens</td>
            </tr>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.userid}</td>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.email}</td>
                    <td>${u.team.stringValue}</td>
                    <td>${u.role.stringValue}</td>
                    <td><a href="Controller?command=Update&id=${u.userid}" id="update${u.userid}">Wijzig</a></td>
                    <td><a href="Controller?command=Delete&id=${u.userid}" id="remove${u.userid}">Verwijder</a></td>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>