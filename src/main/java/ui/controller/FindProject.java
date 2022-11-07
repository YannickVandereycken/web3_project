package ui.controller;

import domain.service.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

public class FindProject extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<String> errors = new ArrayList<String>();
        String date_string = request.getParameter("date");
        try{
            request.setAttribute("result",service.findProject(LocalDate.parse(date_string)));
            return "result.jsp";
        }
        catch (DbException e){
            errors.add(e.getMessage());
        }
        request.setAttribute("errors", errors);
        return "Controller?command=ProjectOverview";
    }
}