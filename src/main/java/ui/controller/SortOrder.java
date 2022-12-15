package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);
        if (request.getParameter("label").isEmpty() || request.getParameter("order").isEmpty()) {
            request.setAttribute("errors", "Sorting label or order can't be empty");
            return "Controller?command=OrderOverview";
        }
        User loggedIn = (User) request.getSession().getAttribute("user");
        request.setAttribute("orders", service.sortWorkOrdersOfTeam(request.getParameter("order"),loggedIn.getTeam()));
        if (loggedIn.getRole() == Role.DIRECTOR)
            request.setAttribute("orders", service.sortWorkOrders(request.getParameter("order")));
        return "orders.jsp";
    }
}