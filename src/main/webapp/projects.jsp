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
        <jsp:param name="current" value=""/>
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
                            <td>${p.projectId}</td>
                            <td>${p.name}</td>
                            <td>${p.team.stringValue}</td>
                            <td>${p.startDate}</td>
                            <td>${not empty p.endDate? p.endDate : ""}</td>
                            <td><a href="Controller?command=UpdateProject&id=${p.projectId}" id="update${p.projectId}">Edit</a></td>
                            <td><a href="Controller?command=DeleteProject&id=${p.projectId}" id="delete${p.projectId}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
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