<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Edit Project</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Edit Project</h2>
    <main>
        <h3>Edit ${update.name} (${update.team})</h3>
        <%--<div class="alert-danger"></div>--%>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger">${e}</p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <%--<p><label for="date">Date</label><input type="date" id="date" name="date" value="${update.dateSQL}" required
                                                    autofocus></p>--%>
            <p><label for="startdate">Start time</label><input type="date" id="startdate" name="startdate"
                                                               value="${update.startDate}" required></p>
            <p><label for="enddate">End time</label><input type="date" id="enddate" name="enddate"
                                                           value="${update.endDate}" required></p>
            <input type="hidden" name="name" value="${update.name}">
            <input type="hidden" name="team" value="${update.team}">
            <input type="hidden" name="id" value="${update.workOrderId}">
            <input type="hidden" name="command" value="UpdateProject">
            <p><input type="submit" id="update" value="Update"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>