package ui.controller;

import domain.service.AppService;
import domain.service.ProjectServiceOld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    protected AppService service;
    protected ProjectServiceOld projectService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public AppService getService() {
        return service;
    }

    public void setService(AppService service) {
        this.service = service;
    }

    public ProjectServiceOld getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectServiceOld service) {
        this.projectService = service;
    }
}