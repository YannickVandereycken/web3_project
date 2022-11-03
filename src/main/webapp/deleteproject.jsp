<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Delete Project</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Delete Project</h2>
    <main>
        <h3>Are you sure you want to delete this project?</h3>
        <p>Name: ${delete.name}</p>
        <p>ProjectId: ${delete.projectId}</p>
        <p>Team: ${delete.team.stringValue}</p>
        <p>From: ${delete.startDate} To: ${delete.endDate}</p>
        <form action="Controller" method="post" novalidate="novalidate">
            <input type="hidden" name="id" value="${delete.projectId}">
            <input type="hidden" name="command" value="DeleteProject">
            <input type="submit" name="submit" id="cancel" value="Cancel" class="cancel">
            <input type="submit" name="submit" id="confirm" value="Confirm" class="confirm">
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>