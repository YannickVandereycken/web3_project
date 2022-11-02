package domain.service;

import domain.model.WorkOrder;

import java.util.ArrayList;

public interface WorkOrderService {
    WorkOrder get(int userid);

    ArrayList<WorkOrder> getAllWorkOrders();

    void add(WorkOrder workOrder);

    void update(WorkOrder workOrder);

    void delete(int workOrderId);

    int getNumberOfWorkorders();
}