package ui.controller;

import domain.model.Role;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;

public class FindProject extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        ArrayList<String> errors = new ArrayList<>();
        String date_string = request.getParameter("date");
        if (date_string.isEmpty()) errors.add("Please fill in a date");
        else {
            try {
                request.setAttribute("result", service.findProject(LocalDate.parse(date_string)));
                return "result.jsp";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=ProjectOverview";
    }
}