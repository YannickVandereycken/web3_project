<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Project Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Projects"/>
    </jsp:include>
    <h2>Project Overview</h2>
    <main>
        <c:choose>
            <c:when test="${not empty projects}">
                <table>
                    <caption>Users Overview</caption>
                    <tr>
                        <th>Project ID</th>
                        <th>Name</th>
                        <th>Team</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="p" items="${projects}">
                        <tr>
                            <td><c:out value='${p.projectId}'/></td>
                            <td><c:out value='${p.name}'/></td>
                            <td><c:out value='${p.team.stringValue}'/></td>
                            <td><c:out value='${p.startDateShort}'/></td>
                            <td><c:out value='${not empty p.endDateShort? p.endDateShort : ""}'/></td>
                            <td><a href="Controller?command=UpdateP&id=${p.projectId}" id="update${p.projectId}">Edit</a></td>
                            <td><a href="Controller?command=DeleteP&id=${p.projectId}" id="delete${p.projectId}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:forEach var="e" items="${errors}">
                    <p class="alert-danger"><c:out value='${e}'/></p>
                </c:forEach>
                <form action="Controller" method="post" novalidate="novalidate">
                    <label for="date">Date: </label><input type="date" name="date" id="date">
                    <input type="hidden" name="command" value="FindProject">
                    <p><input type="submit" value="Find" id="find"></p>
                </form>
            </c:when>
            <c:otherwise>
                <p>These aren't the projects you are looking for.</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>