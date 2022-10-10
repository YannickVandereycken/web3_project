package ui.controller;

import domain.service.ProjectService;
import domain.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected UserService service;
    protected ProjectService projectService;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public UserService getService() {
        return service;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public ProjectService getProjectService() {return projectService;}

    public void setProjectService(ProjectService service){this.projectService=service;}
}
