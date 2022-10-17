package ui.controller;

import domain.model.Project;
import domain.model.Role;
import domain.model.User;
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
        validateName(p, request, errors);
        validateStartDate(p, request, errors);
        //validateEndDate(p, request, errors);
        String username = (String) session.getAttribute("username");
        for (User u : service.getAll()) {
            if (u.getFirstName().equals(username)) {
                p.setTeam(u.getTeam());
                break;
            }
        }
        if (errors.size() == 0) {
            try {
                projectService.add(p);
                return "Controller?command=ProjectOverview";
            } catch (DbException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=RegisterP";
    }

    private void validateName(Project project, HttpServletRequest request, ArrayList<String> errors) {
        String name = request.getParameter("name");
        try {
            project.setName(name);
            request.setAttribute("namePrevious", name);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("nameError", true);
        }
    }

    private void validateStartDate(Project project, HttpServletRequest request, ArrayList<String> errors){
        String string_startDate = request.getParameter("startDate");
        LocalDate startDate = LocalDate.parse(string_startDate);
        try {
            project.setStartDate(startDate);
        } catch (IllegalArgumentException e){
            errors.add(e.getMessage());
            request.setAttribute("startError", true);
        }
    }
}