package ui.controller;

import domain.model.DomainException;
import domain.model.Project;
import domain.model.Role;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateProject extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException{
        Role[] roles = { Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR };
        Utility.checkRole(request, roles);
        HttpSession session = request.getSession();
        ArrayList<String> errors = new ArrayList<String>();
        Project project = new Project();
        project.setProjectId(Integer.parseInt(request.getParameter("id")));
        validateTeam(project, request, errors);
        try {
            if (request.getParameter("name").isEmpty()) errors.add("Please fill in a valid name");
            else{
                project.setName(request.getParameter("name"));
            }
            if (request.getParameter("startdate").isEmpty()) errors.add("Please fill in a valid start date");
            else {
                project.setStartDate(LocalDate.parse(request.getParameter("startdate")));
                request.setAttribute("startdatePrevious", request.getParameter("startdate"));
            }
            if (request.getParameter("enddate").isEmpty()) errors.add("Please fill in a valid end date");
            else {
                validateEndDate(project, request, errors);
            }
        } catch (DbException | IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        if (errors.size() == 0) {
            try {
                service.updateProject(project);
                response.sendRedirect("Controller?command=ProjectOverview");
                return "Controller?command=ProjectOverview";
            } catch (DomainException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=UpdateP";
    }

    private void validateTeam(Project project, HttpServletRequest request, ArrayList<String> errors) {
        String team = request.getParameter("team");
        if (team.isEmpty()) errors.add("Please log in to register a project");
        else {
            project.setTeam(request.getParameter("team"));
        }
    }

    private void validateEndDate(Project project, HttpServletRequest request, ArrayList<String> errors) {
        String enddate = request.getParameter("enddate");
        try {
            project.setEndDate(LocalDate.parse(enddate));
            request.setAttribute("enddatePrevious", enddate);
        } catch (DomainException | IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("enddateError", true);
        }
    }
}
