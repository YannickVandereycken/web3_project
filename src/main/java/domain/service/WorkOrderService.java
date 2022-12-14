package domain.service;

import domain.model.Team;
import domain.model.WorkOrder;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public interface WorkOrderService {
    WorkOrder get(int userid);

    ArrayList<WorkOrder> getAllWorkOrders();

    void add(WorkOrder workOrder);

    ArrayList<WorkOrder> getWorkOrdersOfTeam(Team team);

    void update(WorkOrder workOrder);

    void delete(int workOrderId);

    int getNumberOfWorkorders();

    void checkOverlap(WorkOrder workOrder, Date date, LocalTime startTime, LocalTime endTime);

    void checkPast(Date date, LocalTime endTime);

    ArrayList<WorkOrder> sortWorkOrders(String label, String order);

    ArrayList<WorkOrder> sortWorkOrdersOfTeam(String label, String order, Team team);
}