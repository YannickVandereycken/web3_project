package ui.controller;

import domain.model.*;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException {
        Role[] roles = {Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        ArrayList<String> errors = new ArrayList<>();
        Project p = new Project();
        validateNameTeam(p, request, errors);
        validateDate(p, request, errors);
        if (errors.size() == 0) {
            try {
                service.addProject(p);
                response.sendRedirect("Controller?command=ProjectOverview");
                return "Controller?command=ProjectOverview";
            } catch (DbException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=RegisterP";
    }

    private void validateNameTeam(Project project, HttpServletRequest request, ArrayList<String> errors) {
        Team team = null;
        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            team = user.getTeam();
        }
        String name = request.getParameter("name");
        if (team == null) errors.add("Please log in to register a project");
        try {
            request.setAttribute("namePrevious", name);
            service.checkUnique(name, team);
            project.setName(name);
            project.setTeam(team);
        } catch (DomainException | IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("nameError", true);
        }
    }

    private void validateDate(Project project, HttpServletRequest request, ArrayList<String> errors) {
        String string_startDate = request.getParameter("startdate");
        String string_endDate = request.getParameter("enddate");
        try {
            if (string_startDate.isEmpty() || string_endDate.isEmpty())
                throw new IllegalArgumentException("please fill in a date");
            LocalDate startDate = LocalDate.parse(string_startDate);
            LocalDate endDate = LocalDate.parse(string_endDate);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            request.setAttribute("startdatePrevious", string_startDate);
            request.setAttribute("enddatePrevious", string_endDate);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("startError", true);
        }
    }
}