<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Register Project</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Add Project"/>
    </jsp:include>
    <h2>Register Project</h2>
    <main>
        <p>Do you want to register a project, <c:out value='${user.firstName}'/> (<c:out value='${user.team}'/>)?</p>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger"><c:out value='${e}'/></p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <p><label for="name">Name</label><input type="text" id="name" name="name"
                                                    value="<c:out value='${namePrevious}'/>" required autofocus></p>
            <p><label for="startdate">Start date</label><input type="date" id="startdate" name="startdate"
                                                               value="<c:out value='${startdatePrevious}'/>" required></p>
            <p><label for="enddate">End date</label><input type="date" id="enddate" name="enddate"
                                                           value="<c:out value='${enddatePrevious}'/>" required></p>
            <input type="hidden" name="team" value="${user.team}">
            <input type="hidden" name="command" value="RegisterProject">
            <p><input type="submit" id="register" value="Register"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>