<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Work Orders</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Work Order Overview</h2>
    <main>
        <c:choose>
            <c:when test="${not empty orders}">
                <table>
                    <caption>Users Overview</caption>
                    <tr>
                        <th>Workorder ID</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Duration</th>
                        <th>Description</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="o" items="${orders}">
                        <tr>
                            <td>${o.workOrderId}</td>
                            <td>${o.name} (${o.team.stringValue})</td>
                            <td>${o.date}</td>
                            <td>from ${o.startTime} to ${o.endTime}</td>
                            <td>${o.totalTime} min.</td>
                            <td>${o.description}</td>
                            <td><a href="Controller?command=UpdateO&id=${o.workOrderId}"
                                   id="update${o.workOrderId}">Edit</a></td>
                            <td><a href="Controller?command=DeleteO&id=${o.workOrderId}"
                                   id="delete${o.workOrderId}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <p>These aren't the work orders you are looking for.</p>
            </c:otherwise>
        </c:choose>
        <p>If you want to sort the work orders differently, <a href="Controller?command=SortO">click here</a></p>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>