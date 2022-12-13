package ui.controller;

import domain.model.Project;
import domain.model.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateP extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.DIRECTOR};
        Utility.checkRole(request, roles);

        if (request.getParameter("id") == null || service.getProject(Integer.parseInt(request.getParameter("id"))) == null)
            return "Controller?command=ProjectOverview";

        Project project = service.getProject(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("update", project);
        return "updateproject.jsp";
    }
}
