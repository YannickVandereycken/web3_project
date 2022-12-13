package ui.controller;

import domain.model.Project;
import domain.model.Role;
import domain.model.WorkOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteP extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);
        HttpSession session = request.getSession();
        Project project = service.getProject(Integer.parseInt(request.getParameter("id")));
        if (project == null)
            return "Controller?command=ProjectOverview";
        request.setAttribute("delete", project);
        return "deleteproject.jsp";
    }
}
