package ui.controller;

import domain.service.AppService;
import domain.service.ProjectService;
import domain.service.UserService;
import domain.service.WorkOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected AppService service;
    protected ProjectService projectService;

    protected WorkOrderService workOrderService;

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

    public WorkOrderService getWorkOrderService() {
        return workOrderService;
    }

    public void setWorkOrderService(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }
}