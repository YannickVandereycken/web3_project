<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>
        <span>Company App</span>
    </h1>
    <nav>
        <ul>
            <li ${param.actual eq 'Home'?"id = actual":""}><a href="Controller">Home</a></li>
            <li ${param.actual eq 'Users'?"id = actual":""}><a href="Controller?command=Overview">Users</a></li>
            <li ${param.actual eq 'Register'?"id = actual":""}><a href="Controller?command=Register">Register</a></li>
            <c:if test="${user.role=='EMPLOYEE' || user.role=='TEAMLEADER' || user.role=='DIRECTOR'}">
                <li ${param.actual eq 'Projects'?"id = actual":""}><a href="Controller?command=ProjectOverview">Projects</a></li>
            </c:if>
            <c:if test="${user.role=='TEAMLEADER' || user.role=='DIRECTOR'}">
                <li ${param.actual eq 'Add Project'?"id = actual":""}><a href="Controller?command=RegisterP">Add Project</a></li>
            </c:if>
            <c:if test="${user.role=='EMPLOYEE' || user.role=='TEAMLEADER' || user.role=='DIRECTOR'}">
                <li ${param.actual eq 'Work Orders'?"id = actual":""}><a href="Controller?command=OrderOverview">Work Orders</a></li>
                <li ${param.actual eq 'Add Work Order'?"id = actual":""}><a href="Controller?command=RegisterO">Add Work Order</a></li>
            </c:if>
        </ul>
    </nav>
</header>