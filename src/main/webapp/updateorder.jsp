<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Edit Workorder</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Edit Workorder</h2>
    <main>
        <h3>Edit work order of <c:out value='${update.name}'/> (<c:out value='${update.team}'/>)</h3>
        <%--<div class="alert-danger"></div>--%>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger"><c:out value='${e}'/></p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <p><label for="date">Date</label><input type="date" id="date" name="date" value="<c:out value='${update.dateSQL}'/>" required
                                                    autofocus></p>
            <p><label for="starttime">Start time</label><input type="time" id="starttime" name="starttime"
                                                               value="<c:out value='${update.startTime}'/>" required></p>
            <p><label for="endtime">End time</label><input type="time" id="endtime" name="endtime"
                                                           value="<c:out value='${update.endTime}'/>" required></p>
            <p><label for="description">Description</label><input type="text" id="description" name="description"
                                                                  value="<c:out value='${update.description}'/>"></p>
            <input type="hidden" name="name" value="${update.name}">
            <input type="hidden" name="team" value="${update.team}">
            <input type="hidden" name="id" value="${update.workOrderId}">
            <input type="hidden" name="command" value="UpdateOrder">
            <p><input type="submit" id="update" value="Update"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>