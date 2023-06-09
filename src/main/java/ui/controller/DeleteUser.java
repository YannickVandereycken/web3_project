package ui.controller;

import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException {
        Role[] roles = {Role.DIRECTOR};
        Utility.checkRole(request, roles);

        int id = Integer.parseInt(request.getParameter("id"));
        if (request.getParameter("submit").equals("Confirm")) {
            service.deleteUser(id);
        }
        response.sendRedirect("Controller?command=Overview");
        return "Controller?command=Overview";
    }
}