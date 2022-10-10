package ui.controller;

import domain.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected UserService service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }
}
