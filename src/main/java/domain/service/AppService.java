package domain.service;

import domain.model.Project;
import domain.model.Team;
import domain.model.User;
import domain.model.WorkOrder;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public class AppService {
    private UserService userService = new UserServiceDBSQL();
    private WorkOrderService workOrderService = new WorkOrderServiceDBSQL();
    private ProjectService projectService = new ProjectServiceDBSQL();

    //User Services
    public void addUser(User user) {
        userService.add(user);
    }

    public User getUser(int id) {
        return userService.get(id);
    }

    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void updateUser(User user) {
        userService.update(user);
    }

    public void deleteUser(int id) {
        userService.delete(id);
    }

    public void uniqueEditEmail(String email, int userid) {
        userService.uniqueEditEmail(email, userid);
    }

    public void uniqueEmail(String email) {
        userService.uniqueEmail(email);
    }

    //Workorder services
    public void addOrder(WorkOrder workOrder) {
        workOrderService.add(workOrder);
    }

    public WorkOrder getOrder(int id) {
        return workOrderService.get(id);
    }

    public ArrayList<WorkOrder> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    public void updateOrder(WorkOrder workOrder) {
        workOrderService.update(workOrder);
    }

    public void deleteOrder(int id) {
        workOrderService.delete(id);
    }

    public void checkOverlap(WorkOrder workOrder, Date date, LocalTime endTime) {
        workOrderService.checkOverlap(workOrder, date, endTime);
    }

    public void checkPast(Date date, LocalTime endTime) {
        workOrderService.checkPast(date, endTime);
    }

    public ArrayList<WorkOrder> sortWorkOrders(String label, String order) {
        return workOrderService.sortWorkOrders(label, order);
    }

    //Project Services
    public void addProject(Project project) {
        projectService.add(project);
    }

    public Project getProject(int id) {
        return projectService.get(id);
    }

    public ArrayList<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    public void updateProject(Project project) {
        projectService.update(project);
    }

    public void deleteProject(int id) {
        projectService.delete(id);
    }

    public void checkUnique(String name, Team team) {
        projectService.checkUnique(name, team);
    }
}