package ui.controller;

import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);
        if (request.getParameter("label").isEmpty() || request.getParameter("order").isEmpty()) {
            request.setAttribute("errors", "Sorting label or order can't be empty");
            return "sortorders.jsp";
        }
        request.setAttribute("orders", service.sortWorkOrders(request.getParameter("label"), request.getParameter("order")));
        return "orders.jsp";
    }
}