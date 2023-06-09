package ui.controller;

import domain.model.*;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class UpdateOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        User loggedIn = (User) request.getSession().getAttribute("user");
        if (loggedIn.getRole() == Role.EMPLOYEE && loggedIn.getFirstName() != request.getParameter("name") && loggedIn.getTeam() != Team.valueOf(request.getParameter("team")))
            throw new NotAuthorizedException();
        if (loggedIn.getRole() == Role.TEAMLEADER && loggedIn.getTeam() != Team.valueOf(request.getParameter("team")))
            throw new NotAuthorizedException();

        ArrayList<String> errors = new ArrayList<>();
        WorkOrder workOrder = new WorkOrder();
        workOrder.setWorkOrderId(Integer.parseInt(request.getParameter("id")));
        validateNameTeam(workOrder, request, errors);
        try {
            if (request.getParameter("date").isEmpty()) errors.add("Please fill in a valid date");
            else {
                workOrder.setDate(LocalDate.parse(request.getParameter("date")));
                request.setAttribute("datePrevious", request.getParameter("date"));
            }
            if (request.getParameter("starttime").isEmpty()) errors.add("Please fill in a valid start time");
            else {
                workOrder.setStartTime(LocalTime.parse(request.getParameter("starttime")));
                request.setAttribute("starttimePrevious", request.getParameter("starttime"));
            }
            if (request.getParameter("endtime").isEmpty()) errors.add("Please fill in a valid end time");
            else {
                validateEndTime(workOrder, request, errors);
            }
        } catch (DbException | IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        if (!request.getParameter("endtime").isEmpty() && !request.getParameter("date").isEmpty()) {
            validateOverlap(workOrder, request, errors);
            validatePast(workOrder, request, errors);
        }
        validateDescription(workOrder, request, errors);
        if (errors.size() == 0) {
            try {
                service.updateOrder(workOrder);
                response.sendRedirect("Controller?command=OrderOverview");
                return "Controller?command=OrderOverview";
            } catch (DomainException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=UpdateO";
    }

    private void validateNameTeam(WorkOrder workOrder, HttpServletRequest request, ArrayList<String> errors) {
        String name = request.getParameter("name");
        String team = request.getParameter("team");

        User loggedIn = (User) request.getSession().getAttribute("user");
        if (loggedIn.getRole() == Role.EMPLOYEE || loggedIn.getRole() == Role.TEAMLEADER)
            team = loggedIn.getTeam().getStringValue();

        if (name.isEmpty() || team.isEmpty()) errors.add("Please log in to register a workorder");
        else {
            workOrder.setName(request.getParameter("name"));
            workOrder.setTeam(request.getParameter("team"));
        }
    }

    private void validateEndTime(WorkOrder workOrder, HttpServletRequest request, ArrayList<String> errors) {
        String endtime = request.getParameter("endtime");
        try {
            workOrder.setEndTime(LocalTime.parse(endtime));
            request.setAttribute("endtimePrevious", endtime);
        } catch (DomainException | IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("endtimeError", true);
        }
    }

    private void validateOverlap(WorkOrder workOrder, HttpServletRequest request, ArrayList<String> errors) {
        try {
            service.checkOverlap(workOrder, Date.valueOf(request.getParameter("date")), LocalTime.parse(request.getParameter("starttime")), LocalTime.parse(request.getParameter("endtime")));
        } catch (DbException e) {
            errors.add(e.getMessage());
        }
    }

    private void validatePast(WorkOrder workOrder, HttpServletRequest request, ArrayList<String> errors) {
        try {
            service.checkPast(Date.valueOf(request.getParameter("date")), LocalTime.parse(request.getParameter("endtime")));
        } catch (DbException | IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void validateDescription(WorkOrder workOrder, HttpServletRequest request, ArrayList<String> errors) {
        String description = request.getParameter("description");
        try {
            workOrder.setDescription(description);
            request.setAttribute("descriptionPrevious", description);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("descriptionError", true);
        }
    }
}