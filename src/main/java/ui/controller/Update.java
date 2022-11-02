package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Update extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = service.getUser(Integer.parseInt(request.getParameter("id")));
        if (user==null)
            return "Controller?command=Overview";
        request.setAttribute("update", user);
        return "update.jsp";
    }
}
