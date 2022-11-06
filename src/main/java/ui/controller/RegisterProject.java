package ui.controller;

import domain.model.*;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterProject extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<String> errors = new ArrayList<String>();
        Project p = new Project();
        validateNameTeam(p, request, errors);
        validateDate(p, request, errors);
        if (errors.size() == 0) {
            try {
                service.addProject(p);
                return "Controller?command=ProjectOverview";
            } catch (DbException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=RegisterP";
    }

    private void validateNameTeam(Project project, HttpServletRequest request, ArrayList<String> errors) {
        String name = request.getParameter("name");
        String team = request.getParameter("team");
        try {
            service.checkUnique(name, Team.valueOf(team));
            project.setName(name);
            project.setTeam(team);
            request.setAttribute("namePrevious", name);
        } catch (DomainException | IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("nameError", true);
        }
    }

    private void validateDate(Project project, HttpServletRequest request, ArrayList<String> errors){
        String string_startDate = request.getParameter("startdate");
        String string_endDate = request.getParameter("enddate");
        try {
            LocalDate startDate = LocalDate.parse(string_startDate);
            LocalDate endDate = LocalDate.parse(string_endDate);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
        } catch (IllegalArgumentException e){
            errors.add(e.getMessage());
            request.setAttribute("startError", true);
        }
    }
}