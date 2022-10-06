package be.ucll.project.domain.controller;

import be.ucll.project.domain.model.Role;
import be.ucll.project.domain.model.User;
import be.ucll.project.domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class UpdateUser extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<String> errors = new ArrayList<String>();
        User user = new User();
        user.setUserid(Integer.parseInt(request.getParameter("id")));
        validateName(user, request, errors);
        validateLastName(user, request, errors);
        validateEmail(user, request, errors);
//        validatePassword(user, request, errors);
        validateTeam(user, request, errors);
        validateRole(user, request, errors);
        if (errors.size() == 0) {
            try {
                service.update(user);
                return "Controller?command=Overview";
            } catch (IllegalArgumentException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Update";
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
            service.uniqueEditEmail(email, user.getUserid());
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

/*    private void validatePassword(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            user.setPassword(password);
            request.setAttribute("passwordPrevious", password);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("passwordError", true);
        }
    }*/

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

    private void validateRole(User user, HttpServletRequest request, ArrayList<String> errors) {
        String role = request.getParameter("role");
        try {
            user.setRole(Role.valueOf(role));
            request.setAttribute("rolePrevious", role);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("roleError", true);
        }
    }
}
