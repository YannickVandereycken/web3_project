<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="current" value=""/>
</jsp:include>
<h1>Home</h1>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>