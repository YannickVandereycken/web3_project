package ui.controller;

import domain.model.WorkOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateO extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        WorkOrder workOrder = service.getOrder(Integer.parseInt(request.getParameter("id")));
        if (workOrder == null)
            return "Controller?command=OrderOverview";
        request.setAttribute("update", workOrder);
        return "updateorder.jsp";
    }
}
