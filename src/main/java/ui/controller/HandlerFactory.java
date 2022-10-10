package ui.controller;

import domain.service.UserService;

public class HandlerFactory {

    public RequestHandler getHandler(String command, UserService service) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("be.ucll.project.domain.controller." + command);
            Object objectHandler = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) objectHandler;
            handler.setService(service);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!");
        }
        return handler;
    }
}
