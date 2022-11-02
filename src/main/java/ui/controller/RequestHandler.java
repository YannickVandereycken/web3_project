package ui.controller;

import domain.service.AppService;
import domain.service.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected AppService service;
    protected ProjectService projectService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public AppService getService() {
        return service;
    }

    public void setService(AppService service) {
        this.service = service;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService service) {
        this.projectService = service;
    }
}