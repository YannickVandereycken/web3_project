package domain.service;

import domain.model.WorkOrder;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public interface WorkOrderService {
    WorkOrder get(int userid);

    ArrayList<WorkOrder> getAllWorkOrders();

    void add(WorkOrder workOrder);

    void update(WorkOrder workOrder);

    void delete(int workOrderId);

    int getNumberOfWorkorders();

    void checkOverlap(Date date, LocalTime endTime);

    void checkPast(Date date, LocalTime endTime);
}