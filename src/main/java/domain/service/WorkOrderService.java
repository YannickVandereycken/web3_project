package domain.service;

import domain.model.WorkOrder;

import java.util.List;

public interface WorkOrderService {
    WorkOrder get(int userid);

    List<WorkOrder> getAllWorkOrders();

    void add(WorkOrder workOrder);

    void update(WorkOrder workOrder);

    void delete(int workOrderId);

    int getNumberOfWorkorders();
}