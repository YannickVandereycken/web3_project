package ui.controller;

import domain.service.AppService;
import domain.service.ProjectService;
import domain.service.UserService;
import domain.service.WorkOrderService;

public class HandlerFactory {

    public RequestHandler getHandler(String command, AppService service, ProjectService projectService, WorkOrderService workOrderService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + command);
            Object objectHandler = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) objectHandler;
            handler.setService(service);
            handler.setProjectService(projectService);
            handler.setWorkOrderService(workOrderService);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!");
        }
        return handler;
    }
}