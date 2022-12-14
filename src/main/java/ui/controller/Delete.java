package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.DIRECTOR};
        Utility.checkRole(request, roles);

        User user = service.getUser(Integer.parseInt(request.getParameter("id")));
        if (user == null)
            return "Controller?command=Overview";
        request.setAttribute("delete", user);
        return "delete.jsp";
    }
}