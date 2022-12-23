package ui.controller;

import domain.model.Role;
import domain.model.User;
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
        User loggedIn = (User) request.getSession().getAttribute("user");
        if (date_string.isEmpty()) errors.add("Please fill in a date");
        else {
            try {
                if (loggedIn.getRole() == Role.TEAMLEADER || loggedIn.getRole() == Role.DIRECTOR)
                    request.setAttribute("result", service.findProject(LocalDate.parse(date_string)));
                else
                    request.setAttribute("result", service.findProjectOfTeam(LocalDate.parse(date_string), loggedIn.getTeam()));
                return "result.jsp";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=ProjectOverview";
    }
}