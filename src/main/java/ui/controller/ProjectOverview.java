package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("projects", service.getProjectsOfTeam(user.getTeam()));
        if (user.getRole() == Role.TEAMLEADER || user.getRole() == Role.DIRECTOR)
            request.setAttribute("projects", service.getAllProjects());
        return "projects.jsp";
    }
}