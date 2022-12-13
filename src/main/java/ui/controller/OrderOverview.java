package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("orders", service.getWorkOrdersOfTeam(user.getTeam()));
        if (user.getRole() == Role.DIRECTOR)
            request.setAttribute("orders", service.getAllWorkOrders());
        return "orders.jsp";
    }
}