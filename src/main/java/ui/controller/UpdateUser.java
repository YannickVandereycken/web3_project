package ui.controller;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;
import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException {
        Role[] roles = {Role.EMPLOYEE, Role.TEAMLEADER, Role.DIRECTOR};
        Utility.checkRole(request, roles);

        ArrayList<String> errors = new ArrayList<>();
        User loggedIn = (User) request.getSession().getAttribute("user");
        if (loggedIn.getRole() == Role.EMPLOYEE && loggedIn.getUserid() != Integer.parseInt(request.getParameter("id")))
            throw new NotAuthorizedException();
        if (loggedIn.getRole() == Role.TEAMLEADER && !loggedIn.getTeam().getStringValue().equalsIgnoreCase(request.getParameter("team")))
            throw new NotAuthorizedException();
        User user = new User();
        user.setUserid(Integer.parseInt(request.getParameter("id")));
        validateName(user, request, errors);
        validateLastName(user, request, errors);
        validateEmail(user, request, errors);
//        validatePassword(user, request, errors);
        validateRole(user, request, errors);
        validateTeam(user, request, errors);
        if (errors.size() == 0) {
            try {
                service.updateUser(user);
                response.sendRedirect("Controller?command=Overview");
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
        } catch (DbException | IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("emailError", true);
        }
    }

    private void validateTeam(User user, HttpServletRequest request, ArrayList<String> errors) {
        String team = request.getParameter("team");

        User loggedIn = (User) request.getSession().getAttribute("user");
        if (loggedIn.getRole() == Role.EMPLOYEE || loggedIn.getRole() == Role.TEAMLEADER)
            team = loggedIn.getTeam().getStringValue();

        if (request.getParameter("role").equals("DIRECTOR"))
            team = "ALPHA";
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

        User loggedIn = (User) request.getSession().getAttribute("user");
        if (loggedIn.getRole() == Role.EMPLOYEE)
            role = Role.EMPLOYEE.getStringValue();
        if (loggedIn.getRole() == Role.TEAMLEADER && request.getParameter("role").equalsIgnoreCase("director"))
            throw new NotAuthorizedException();

        try {
            user.setRole(Role.valueOf(role));
            request.setAttribute("rolePrevious", role);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("roleError", true);
        }
    }
    /*  private void validatePassword(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            user.setPassword(password);
            request.setAttribute("passwordPrevious", password);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            request.setAttribute("passwordError", true);
        }
    }*/
}
