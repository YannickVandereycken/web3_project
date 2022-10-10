package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LogIn extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean succesful = false;
        List<User> users= service.getAll();
        for(User u : users){
            if(u.getEmail().equals(email) && u.isCorrectPassword(password)){
                session.setAttribute("username", u.getFirstName());
                succesful=true;
            }
        }
        if(succesful==false)
            request.setAttribute("error", "No valid email/password");
        return "index.jsp";
    }
}
