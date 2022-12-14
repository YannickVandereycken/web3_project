package ui.controller;

import domain.model.Project;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteP extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.DIRECTOR};
        Utility.checkRole(request, roles);

        Project project = service.getProject(Integer.parseInt(request.getParameter("id")));
        if (project == null)
            return "Controller?command=ProjectOverview";
        request.setAttribute("delete", project);
        return "deleteproject.jsp";
    }
}