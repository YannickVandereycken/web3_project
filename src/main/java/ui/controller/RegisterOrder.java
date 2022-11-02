package ui.controller;

import domain.model.WorkOrder;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class RegisterOrder extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<String> errors = new ArrayList<String>();
        WorkOrder workOrder = new WorkOrder();
        validateNameTeam(workOrder, request, errors);
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
        validateDescription(workOrder, request, errors);
        if (errors.size() == 0) {
            try {
                workOrderService.add(workOrder);
                return "Controller?command=OrderOverview";
            } catch (DbException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=RegisterO";
    }

    private void validateNameTeam(WorkOrder workOrder, HttpServletRequest request, ArrayList<String> errors) {
        String name = request.getParameter("name");
        String team = request.getParameter("team");
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
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("endtimeError", true);
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