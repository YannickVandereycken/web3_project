package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //TO REMOVE AFTER USING USER FOR EVERYTHING
        session.removeAttribute("username");
        session.removeAttribute("team");
        response.sendRedirect("Controller?command=Index");
        return "index.jsp";
    }
}