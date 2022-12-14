package ui.controller;

import domain.model.Role;
import domain.model.WorkOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteO extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.DIRECTOR};
        Utility.checkRole(request, roles);

        WorkOrder workOrder = service.getOrder(Integer.parseInt(request.getParameter("id")));
        if (workOrder == null)
            return "Controller?command=OrderOverview";
        request.setAttribute("delete", workOrder);
        return "deleteorder.jsp";
    }
}