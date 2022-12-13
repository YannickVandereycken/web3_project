package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        if (request.getParameter("id") == null || service.getUser(Integer.parseInt(request.getParameter("id"))) == null)
            return "Controller?command=Overview";

        User loggedIn = (User) request.getSession().getAttribute("user");
        User user = service.getUser(Integer.parseInt(request.getParameter("id")));
        if ((loggedIn.getRole() == Role.EMPLOYEE) && loggedIn.getUserid() != user.getUserid())
            throw new NotAuthorizedException();
        if ((loggedIn.getRole() == Role.TEAMLEADER && loggedIn.getTeam() != user.getTeam()))
            throw new NotAuthorizedException();

        request.setAttribute("update", user);
        return "update.jsp";
    }
}