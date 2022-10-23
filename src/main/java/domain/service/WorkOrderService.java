package domain.service;

import domain.model.Team;
import domain.model.WorkOrder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkOrderService {

    private final Map<Integer, WorkOrder> workorders = new HashMap<Integer, WorkOrder>();
    private int workOrderId = 1;    // als je later werkt met externe databank, wordt dit workOrderId automatisch gegenereerd

    public WorkOrderService() {
        WorkOrder workOrder1 = new WorkOrder("Anna", Team.ALPHA, LocalDate.of(2022, 9, 21), LocalTime.of(10, 12), LocalTime.of(12, 12), "Big order");
        add(workOrder1);
        WorkOrder workOrder2 = new WorkOrder("Bert", Team.BETA, LocalDate.of(2022, 9, 28), LocalTime.of(10, 0), LocalTime.of(17, 0), "Lorem");
        add(workOrder2);
        WorkOrder workOrder3 = new WorkOrder("Chris", Team.BETA, LocalDate.of(2022, 9, 21), LocalTime.of(9, 0), LocalTime.of(10, 30), "Ipsum");
        add(workOrder3);
    }

    public WorkOrder get(int userid) {
        return workorders.get(userid);
    }

    public List<WorkOrder> getAll() {
        return new ArrayList<WorkOrder>(workorders.values());
    }

    public void add(WorkOrder workOrder) {
        if (workOrder == null) {
            throw new DbException("No workorder given");
        }
        if (workorders.containsKey(workOrder.getWorkOrderId())) {
            throw new DbException("Workorder already exists");
        }
        workOrder.setWorkOrderId(workOrderId);   // workOrder toevoegen geeft altijd nieuw projectid
        workorders.put(workOrder.getWorkOrderId(), workOrder);
        workOrderId++;
    }

    public void update(WorkOrder workOrder) {
        if (workOrder == null) {
            throw new DbException("No workorder given");
        }
        if (!workorders.containsKey(workOrder.getWorkOrderId())) {
            throw new DbException("No workorder found");
        }
        workorders.put(workOrder.getWorkOrderId(), workOrder); // workOrder updaten: workOrderId blijft behouden
    }

    public void delete(int workOrderId) {
        workorders.remove(workOrderId);   // workOrderId gaat verloren, maar wordt niet ingenomen door eventuele nieuw project
    }

    public int getNumberOfProjects() {
        return workorders.size();
    }
}
