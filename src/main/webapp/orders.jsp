<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Work Orders</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="actual" value="Work Orders"/>
    </jsp:include>
    <h2>Work Order Overview</h2>
    <main>
        <c:choose>
            <c:when test="${not empty orders}">
                <table>
                    <caption>Users Overview</caption>
                    <tr>
                        <th>Workorder ID</th>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Duration</th>
                        <th>Description</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="o" items="${orders}">
                        <tr>
                            <td><c:out value='${o.workOrderId}'/></td>
                            <td><c:out value='${o.name}'/> (<c:out value='${o.team.stringValue}'/>)</td>
                            <td><c:out value='${o.date}'/></td>
                            <td>from <c:out value='${o.startTime}'/> to <c:out value='${o.endTime}'/></td>
                            <td><c:out value='${o.totalTime}'/> min.</td>
                            <td><c:out value='${o.description}'/></td>
                            <td><a href="Controller?command=UpdateO&id=${o.workOrderId}"
                                   id="update${o.workOrderId}">Edit</a></td>
                            <td><a href="Controller?command=DeleteO&id=${o.workOrderId}"
                                   id="delete${o.workOrderId}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <h3>Choose how you want to sort the work orders by date</h3>
                <c:forEach var="e" items="${errors}">
                    <p class="alert-danger"><c:out value='${e}'/></p>
                </c:forEach>
                <form action="Controller" method="post" novalidate="novalidate">
                        <%--                    <label for="label">Sorting Label</label>--%>
                        <%--                    <select id="label" name="label">--%>
                        <%--                        <option value="1">Workorder ID</option>--%>
                        <%--                        <option value="2">Name</option>--%>
                        <%--                        <option value="3">Team</option>--%>
                        <%--                        <option value="4">Date</option>--%>
                        <%--                        <option value="5">Start Time</option>--%>
                        <%--                        <option value="6">End Time</option>--%>
                        <%--                        <option value="7">Description</option>--%>
                        <%--                    </select>--%>
                    <label for="order">Order</label>
                    <select id="order" name="order">
                        <option value="asc">Ascending</option>
                        <option value="desc">Descending</option>
                    </select>
                    <input type="hidden" name="label" value="4">
                    <input type="hidden" name="command" value="SortOrder">
                    <p><input type="submit" id="sort" value="Sort"></p>
                </form>
            </c:when>
            <c:otherwise>
                <p>These aren't the work orders you are looking for.</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>