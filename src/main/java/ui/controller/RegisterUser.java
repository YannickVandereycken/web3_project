package ui.controller;

import domain.model.Role;
import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class RegisterUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<String> errors = new ArrayList<>();
        User u = new User();
        validateName(u, request, errors);
        validateLastName(u, request, errors);
        validateEmail(u, request, errors);
        validatePassword(u, request, errors);
        validateTeam(u, request, errors);
        u.setRole(Role.EMPLOYEE);
        if (errors.size() == 0) {
            try {
                service.addUser(u);
                response.sendRedirect("Controller?command=Overview");
                return "Controller?command=Overview";
            } catch (DbException | IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Register";
    }

    private void validateName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            user.setFirstName(firstName);
            request.setAttribute("namePrevious", firstName);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("nameError", true);
        }
    }

    private void validateLastName(User user, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            user.setLastName(lastName);
            request.setAttribute("lastNamePrevious", lastName);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("lastNameError", true);
        }
    }

    private void validateEmail(User user, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            service.uniqueEmail(email);
            user.setEmail(email);
            request.setAttribute("emailPrevious", email);
        } catch (DbException | IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("emailError", true);
        }
    }

    private void validatePassword(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            user.setPassword(password);
            request.setAttribute("passwordPrevious", password);
        } catch (IllegalArgumentException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            errors.add(e.getMessage());
            request.setAttribute("passwordError", true);
        }
    }

    private void validateTeam(User user, HttpServletRequest request, ArrayList<String> errors) {
        String team = request.getParameter("team");
        if (team.equals("ALPHA")) {
            errors.add("Team can't be Alpha");
            request.setAttribute("teamError", true);
        } else {
            try {
                user.setTeam(team);
                request.setAttribute("teamPrevious", team);
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
                request.setAttribute("teamError", true);
            }
        }
    }
}