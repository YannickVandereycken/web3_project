<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Delete Workorder</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Delete Workorder</h2>
    <main>
        <h3>Are you sure you want to delete this workorder?</h3>
        <p>Name: <c:out value='${delete.name}'/> (<c:out value='${delete.team.stringValue}'/>)</p>
        <p>WorkorderId: <c:out value='${delete.workOrderId}'/></p>
        <p>Date: <c:out value='${delete.date}'/></p>
        <p>Time: <c:out value='${delete.startTime}'/> to <c:out value='${delete.endTime}'/></p>
        <form action="Controller" method="post" novalidate="novalidate">
            <input type="hidden" name="id" value="<c:out value='${delete.workOrderId}'/>">
            <input type="hidden" name="command" value="DeleteOrder">
            <input type="submit" name="submit" id="cancel" value="Cancel" class="cancel">
            <input type="submit" name="submit" id="confirm" value="Confirm" class="confirm">
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>