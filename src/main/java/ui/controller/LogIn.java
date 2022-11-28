package ui.controller;

import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean succesful = false;
        List<User> users = service.getAllUsers();
        for (User u : users) {
            try {
                if (u.getEmail().equals(email) && u.isCorrectPassword(password)) {
                    session.setAttribute("username", u.getFirstName());
                    session.setAttribute("team", u.getTeam());
                    succesful = true;
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                throw new DbException(e.getMessage());
            }
        }
        if (succesful == false)
            request.setAttribute("error", "No valid email/password");
        response.sendRedirect("Controller?command=Index");
        return "index.jsp";
    }
}