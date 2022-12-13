package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Update extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        User loggedIn = (User) request.getSession().getAttribute("user");
        User user = service.getUser(Integer.parseInt(request.getParameter("id")));
        if ((loggedIn.getRole() == Role.TEAMLEADER || loggedIn.getRole() == Role.EMPLOYEE) && (user.getRole() == Role.DIRECTOR || loggedIn.getTeam() != user.getTeam()))
            throw new NotAuthorizedException();
        if (user == null)
            return "Controller?command=Overview";
        request.setAttribute("update", user);
        return "update.jsp";
    }
}