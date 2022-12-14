<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Register Order</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Add Work Order"/>
    </jsp:include>
    <h2>Register Work Order</h2>
    <main>
        <p>Do you want to register a workorder, <c:out value='${user.firstName}'/> (<c:out value='${user.team}'/>)?</p>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger"><c:out value='${e}'/></p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <p><label for="date">Date</label><input type="date" id="date" name="date"
                                                    value="<c:out value='${datePrevious}'/>" required autofocus></p>
            <p><label for="starttime">Start time</label><input type="time" id="starttime" name="starttime"
                                                               value="<c:out value='${starttimePrevious}'/>" required></p>
            <p><label for="endtime">End time</label><input type="time" id="endtime" name="endtime"
                                                           value="<c:out value='${endtimePrevious}'/>" required></p>
            <p><label for="description">Description</label><input type="text" id="description" name="description"
                                                                  value="<c:out value='${descriptionPrevious}'/>"></p>
            <input type="hidden" name="name" value="${user.firstName}">
            <input type="hidden" name="team" value="${user.team}">
            <input type="hidden" name="command" value="RegisterOrder">
            <p><input type="submit" id="register" value="Register"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>