<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="UTF-8">
  <title>Result</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
  <jsp:include page="header.jsp">
    <jsp:param name="current" value=""/>
  </jsp:include>
  <h2>Results</h2>
  <main>
    <table>
      <caption>Search Results</caption>
      <tr>
        <th>Workorder ID</th>
        <th>Name</th>
        <th>Date</th>
        <th>Time</th>
        <th>Duration</th>
        <th>Description</th>
      </tr>
      <c:forEach var="o" items="${result}">
        <tr>
          <td><c:out value='${o.workOrderId}'/></td>
          <td><c:out value='${o.name}'/> (<c:out value='${o.team.stringValue}'/>)</td>
          <td><c:out value='${o.date}'/></td>
          <td>from <c:out value='${o.startTime}'/> to <c:out value='${o.endTime}'/></td>
          <td><c:out value='${o.totalTime}'/> min.</td>
          <td><c:out value='${o.description}'/></td>
        </tr>
      </c:forEach>
    </table>
    <c:forEach var="e" items="${errors}">
      <p class="alert-danger"><c:out value='${e}'/></p>
    </c:forEach>
  </main>
  <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
