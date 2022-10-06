<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="current" value=""/>
    </jsp:include>
    <h2>Edit User</h2>
    <main>
        <h3>Userid: ${update.userid}</h3>
        <%--<div class="alert-danger"></div>--%>
        <c:forEach var="e" items="${errors}">
            <p class="alert-danger">${e}</p>
        </c:forEach>
        <form action="Controller" method="post" novalidate="novalidate">
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               value="${update.firstName}" required autofocus></p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                             value="${update.lastName}" required></p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" value="${update.email}"
                                                      required></p>
            <label for="role">Role</label>
            <select id="role" name="role">
                <option value="EMPLOYEE" ${update.role.stringValue=="Employee"?"selected":""}>Employee</option>
                <option value="TEAMLEADER" ${update.role.stringValue=="Teamleader"?"selected":""}>Teamleader</option>
                <option value="DIRECTOR" ${update.role.stringValue=="Director"?"selected":""}>Director</option>
            </select>
            <label for="team">Team</label>
            <select id="team" name="team">
                <option value="ALPHA" ${update.team.stringValue=="Alpha"?"selected":""}>Alpha</option>
                <option value="BETA" ${update.team.stringValue=="Beta"?"selected":""}>Beta</option>
                <option value="GAMMA" ${update.team.stringValue=="Gamma"?"selected":""}>Gamma</option>
                <option value="DELTA" ${update.team.stringValue=="Delta"?"selected":""}>Delta</option>
                <option value="EPSILON" ${update.team.stringValue=="Epsilon"?"selected":""}>Epsilon</option>
            </select>
            <input type="hidden" name="id" value="${update.userid}">
            <input type="hidden" name="command" value="UpdateUser">
            <p><input type="submit" id="signUp" value="Update"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
