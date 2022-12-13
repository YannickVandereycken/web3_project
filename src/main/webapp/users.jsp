<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Users"/>
    </jsp:include>
    <h2>User Overview</h2>
    <main>
        <c:choose>
            <c:when test="${not empty users}">
                <table>
                    <caption>Users Overview</caption>
                    <tr>
                        <th>User ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Team</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td><c:out value='${u.userid}'/></td>
                            <td><c:out value='${u.firstName}'/></td>
                            <td><c:out value='${u.lastName}'/></td>
                            <td><c:out value='${u.email}'/></td>
                            <td><c:out value='${u.team.stringValue}'/></td>
                            <td><c:out value='${u.role.stringValue}'/></td>
                            <td><c:if test="${user.userid==u.userid || (user.role=='TEAMLEADER' && u.role !='DIRECTOR' && user.team==u.team) || user.role=='DIRECTOR'}">
                                <a href="Controller?command=Update&id=${u.userid}" id="update${u.userid}">Edit</a>
                            </c:if></td>
                            <td><c:if test="${user.role=='DIRECTOR'}">
                                <a href="Controller?command=Delete&id=${u.userid}" id="delete${u.userid}">Delete</a>
                            </c:if></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>These aren't the users you are looking for.</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>