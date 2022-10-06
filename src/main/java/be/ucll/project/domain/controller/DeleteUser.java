package be.ucll.project.domain.controller;

import be.ucll.project.domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class DeleteUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        if (request.getParameter("submit").equals("Confirm")) {
            service.delete(id);
        }
        return "Controller?command=Overview";
    }
}
