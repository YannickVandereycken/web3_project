<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Sort Orders</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Sort Work Orders</h2>
    <main>
        <p>Choose how you want to sort the work orders</p>
        <%--<div class="alert-danger"></div>--%>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger">${e}</p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <label for="label">Sorting Label</label>
            <select id="label" name="label">
                <option value="1">Workorder ID</option>
                <option value="2">Name</option>
                <option value="3">Team</option>
                <option value="4">Date</option>
                <option value="5">Start Time</option>
                <option value="6">End Time</option>
                <option value="7">Description</option>
            </select>
            <label for="order">Order</label>
            <select id="order" name="order">
                <option value="asc">Ascending</option>
                <option value="desc">Descending</option>
            </select>
            <input type="hidden" name="command" value="SortOrder">
            <p><input type="submit" id="sort" value="Sort"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>