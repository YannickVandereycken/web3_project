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
    <h2>Results</h2>
    <main>
        <table>
            <caption>Search Results</caption>
            <tr>
                <th>Project ID</th>
                <th>Name</th>
                <th>Team</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            <c:forEach var="p" items="${result}">
                <tr>
                    <td>${p.projectId}</td>
                    <td>${p.name}</td>
                    <td>${p.team.stringValue}</td>
                    <td>${p.startDate}</td>
                    <td>${not empty p.endDate? p.endDate : ""}</td>
                </tr>
            </c:forEach>
        </table>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger">${e}</p>
        </c:forEach>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
