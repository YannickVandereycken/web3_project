package be.ucll.project.domain.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Overview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        request.setAttribute("last" , session.getAttribute("lastAddedUser"));
        request.setAttribute("users", service.getAll());
        return "users.jsp";
    }
}