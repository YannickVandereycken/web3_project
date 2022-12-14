package ui.controller;

import domain.service.AppService;
import domain.service.ProjectServiceOld;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AppService service = new AppService();
    private final ProjectServiceOld projectService = new ProjectServiceOld();
    private final HandlerFactory handlerFactory = new HandlerFactory();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination;
        String command = request.getParameter("command");

        if (command == null || command.isEmpty()) {
            command = "Index";
        }
        RequestHandler handler = handlerFactory.getHandler(command, service, projectService);

        try {
            destination = handler.handleRequest(request, response);
        } catch (NotAuthorizedException e) {
            // alle handlers gooien een NotAuthorizedException als gebruiker niet de juiste rechten heeft
            // zodat authorization altijd op dezelfde manier afgehandeld wordt
            request.setAttribute("notAuthorized", "You have insufficient rights to have a look at the requested page.");
            destination = "Controller?command=Index";
        }

        if (!response.isCommitted()) {
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request, response);
        }
    }
}