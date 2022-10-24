package ui.controller;

import domain.service.AppService;
import domain.service.ProjectService;
import domain.service.UserService;
import domain.service.WorkOrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AppService service = new AppService();
    private ProjectService projectService = new ProjectService();

    private WorkOrderService workOrderService = new WorkOrderService();
    private HandlerFactory handlerFactory = new HandlerFactory();

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
        HttpSession session = request.getSession();
        String destination = "index.jsp";
        String command = request.getParameter("command");

        if (command != null){
            RequestHandler handler = handlerFactory.getHandler(command, service, projectService, workOrderService);
            destination = handler.handleRequest(request, response);
        }

//        if (command != null && session.getAttribute("username") != null) {
//            RequestHandler handler = handlerFactory.getHandler(command, service);
//            destination = handler.handleRequest(request, response);
//        }

//        if (command != null && command.equals("LogIn")) {
//            RequestHandler handler = handlerFactory.getHandler(command, service);
//            destination = handler.handleRequest(request, response);
//        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }
}