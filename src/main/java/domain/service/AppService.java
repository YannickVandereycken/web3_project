package domain.service;

import domain.model.Project;
import domain.model.Team;
import domain.model.User;
import domain.model.WorkOrder;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AppService {
    private final UserService userService = new UserServiceDBSQL();
    private final WorkOrderService workOrderService = new WorkOrderServiceDBSQL();
    private final ProjectService projectService = new ProjectServiceDBSQL();

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

    public ArrayList<WorkOrder> getWorkOrdersOfTeam(Team team) {
        return workOrderService.getWorkOrdersOfTeam(team);
    }

    public void updateOrder(WorkOrder workOrder) {
        workOrderService.update(workOrder);
    }

    public void deleteOrder(int id) {
        workOrderService.delete(id);
    }

    public void checkOverlap(WorkOrder workOrder, Date date, LocalTime startTime, LocalTime endTime) {
        workOrderService.checkOverlap(workOrder, date, startTime, endTime);
    }

    public void checkPast(Date date, LocalTime endTime) {
        workOrderService.checkPast(date, endTime);
    }

    public ArrayList<WorkOrder> sortWorkOrders(String order) {
        return workOrderService.sortWorkOrders(order);
    }

    public ArrayList<WorkOrder> sortWorkOrdersOfTeam(String order, Team team) {
        return workOrderService.sortWorkOrdersOfTeam(order, team);
    }

    public ArrayList<WorkOrder> findOrder(LocalDate date) {return workOrderService.findWorkOrders(date);}

    public ArrayList<WorkOrder> findOrderOfTeam(LocalDate date, Team team) { return workOrderService.findWorkOrdersOfTeam(date, team);}


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

    public ArrayList<Project> getProjectsOfTeam(Team team){
        return projectService.getProjectsOfTeam(team);
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

    public ArrayList<Project> findProject(LocalDate date) {
        return projectService.find(date);
    }

    public ArrayList<Project> findProjectOfTeam(LocalDate date, Team team) {return projectService.findOfTeam(date, team);}

    public ArrayList<Project> sortProjects(String order) {
        return projectService.sort(order);
    }

    public ArrayList<Project> sortProjectsOfTeam(String order, Team team) {
        return projectService.sortOfTeam(team, order);
    }
}