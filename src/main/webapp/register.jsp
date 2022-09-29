<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <jsp:include page="header.jsp">
            <jsp:param name="current" value=""/>
        </jsp:include>
        <h2>Register</h2>
    </header>
    <main>
        <%--<div class="alert-danger"></div>--%>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger">${e}</p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" value="${namePrevious}" required autofocus></p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" value="${lastNamePrevious}" required></p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="${emailPrevious}" required></p>
            <p><label for="password">Password</label><input type="password" id="password" name="password" value="${passwordPrevious}" required></p>
            <label for="team">Team</label>
            <select id="team" name="team">
                <option value="ALPHA">Alpha</option>
                <option value="BETA">Beta</option>
                <option value="GAMMA">Gamma</option>
                <option value="DELTA">Delta</option>
                <option value="EPSILON">Epsilon</option>
            </select>
            <input type="hidden" name="command" value="RegisterUser">
            <p><input type="submit" id="signUp" value="Sign Up"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
