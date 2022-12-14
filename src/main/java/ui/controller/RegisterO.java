package ui.controller;

import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterO extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);
        return "registerorder.jsp";
    }
}