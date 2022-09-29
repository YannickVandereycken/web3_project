package be.ucll.project.domain.controller;

import be.ucll.project.domain.model.Role;
import be.ucll.project.domain.model.User;
import be.ucll.project.domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class RegisterUser extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<String> errors = new ArrayList<String>();
        User u = new User();
        validateName(u, request, errors);
        validateLastName(u, request, errors);
        validateEmail(u, request, errors);
        validatePassword(u, request, errors);
        validateTeam(u, request, errors);
        u.setRole(Role.EMPLOYEE);
        if (errors.size() == 0) {
            try {
                service.add(u);
                return "Controller?command=Overview";
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "register.jsp";
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
        } catch (DbException e) {
            errors.add(e.getMessage());
            request.setAttribute("emailError", true);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("emailError", true);
        }
    }

    private void validatePassword(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            user.setPassword(password);
            request.setAttribute("passwordPrevious", password);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("passwordError", true);
        }
    }

    private void validateTeam(User user, HttpServletRequest request, ArrayList<String> errors) {
        String team = request.getParameter("team");
        try {
            user.setTeam(team);
            request.setAttribute("teamPrevious", team);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("teamError", true);
        }
    }
}
