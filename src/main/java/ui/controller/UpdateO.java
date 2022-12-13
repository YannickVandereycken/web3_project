package ui.controller;

import domain.model.Role;
import domain.model.User;
import domain.model.WorkOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateO extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        if (request.getParameter("id") == null || service.getOrder(Integer.parseInt(request.getParameter("id"))) == null)
            return "Controller?command=OrderOverview";

        User loggedIn = (User) request.getSession().getAttribute("user");
        WorkOrder workOrder = service.getOrder(Integer.parseInt(request.getParameter("id")));
        if (loggedIn.getRole() == Role.EMPLOYEE && loggedIn.getFirstName() != workOrder.getName() && loggedIn.getTeam() != workOrder.getTeam())
            throw new NotAuthorizedException();
        if (loggedIn.getRole() == Role.TEAMLEADER && loggedIn.getTeam() != workOrder.getTeam())
            throw new NotAuthorizedException();

        request.setAttribute("update", workOrder);
        return "updateorder.jsp";
    }
}