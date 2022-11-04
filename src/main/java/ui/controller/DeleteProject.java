package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteProject extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        if (request.getParameter("submit").equals("Confirm")) {
            service.deleteProject(id);
        }
        return "Controller?command=ProjectOverview";
    }
}