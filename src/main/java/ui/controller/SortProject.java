package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortProject extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);
        if (request.getParameter("label").isEmpty() || request.getParameter("project").isEmpty()) {
            request.setAttribute("errors", "Sorting label or project can't be empty");
            return "sortprojects.jsp";
        }
        User loggedIn = (User) request.getSession().getAttribute("user");
        request.setAttribute("projects", service.sortProjectsOfTeam(request.getParameter("project"),loggedIn.getTeam()));
        if (loggedIn.getRole() == Role.DIRECTOR)
            request.setAttribute("projects", service.sortProjects(request.getParameter("project")));
        return "projects.jsp";
    }
}
