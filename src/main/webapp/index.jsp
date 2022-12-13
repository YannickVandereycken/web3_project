<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Home"/>
    </jsp:include>
    <h2>Home</h2>
    <c:if test="${notAuthorized!=null }">
        <p class="error">${notAuthorized}</p>
    </c:if>
    <c:choose>
        <c:when test="${not empty username}">
            <main>
                <h3>Welcome, <c:out value='${username}'/></h3>
                <form action="Controller" method="post">
                    <input type="hidden" name="command" value="LogOut">
                    <p><input type="submit" id="logOut" value="Log Out"></p>
                </form>
            </main>
        </c:when>
        <c:otherwise>
            <main>
                <h3>Please Log In</h3>
                <form action="Controller" method="post">
                    <p><label for="email">E-mail:</label><input id="email" type="text" name="email"></p>
                    <p><label for="password">Wachtwoord:</label><input id="password" type="text" name="password"></p>
                    <input type="hidden" name="command" value="LogIn">
                    <p><input type="submit" id="logIn" value="Log In"></p>
                </form>
            </main>
        </c:otherwise>
    </c:choose>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>