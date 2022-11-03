package domain.service;

import domain.model.User;
import domain.model.WorkOrder;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public class AppService {
    private UserService userService = new UserServiceDBSQL();
    private WorkOrderService workOrderService = new WorkOrderServiceDBSQL();

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

    public void checkOverlap(Date date, LocalTime endTime){
        workOrderService.checkOverlap(date, endTime);
    }

    public void checkPast(Date date, LocalTime endTime) {
        workOrderService.checkPast(date, endTime);
    }

    //Project Services
}