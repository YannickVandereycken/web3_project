package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);
        HttpSession session = request.getSession();
        User user = service.getUser(Integer.parseInt(request.getParameter("id")));
        if (user == null)
            return "Controller?command=Overview";
        request.setAttribute("delete", user);
        return "delete.jsp";
    }
}