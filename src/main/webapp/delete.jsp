<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Delete User</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Delete User</h2>
    <main>
        <h3>Are you sure you want to delete user?</h3>
        <p>Name: ${delete.firstName} ${delete.lastName}</p>
        <p>Userid: ${delete.userid}</p>
        <p>Role: ${delete.role.stringValue}</p>
        <p>Team: ${delete.team.stringValue}</p>
        <form action="Controller" method="post" novalidate="novalidate">
            <input type="hidden" name="id" value="${delete.userid}">
            <input type="hidden" name="command" value="DeleteUser">
            <input type="submit" name="submit" id="cancel" value="Cancel" class="cancel">
            <input type="submit" name="submit" id="confirm" value="Confirm" class="confirm">
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
