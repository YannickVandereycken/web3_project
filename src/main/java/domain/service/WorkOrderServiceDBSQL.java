package domain.service;

import domain.model.Team;
import domain.model.WorkOrder;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WorkOrderServiceDBSQL implements WorkOrderService {

    private final Connection connection;
    private final String schema;

    public WorkOrderServiceDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(WorkOrder workOrder) {
        String query = String.format("insert into %s.workorders (name, team, date, start_time, end_time, description) values (?,?,?,?,?,?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, workOrder.getName());
            statement.setString(2, workOrder.getTeam().getStringValue());
            statement.setDate(3, workOrder.getDateSQL());
            statement.setTime(4, workOrder.getStartTimeSQL());
            statement.setTime(5, workOrder.getEndTimeSQL());
            statement.setString(6, workOrder.getDescription());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public WorkOrder get(int id) {
        for (WorkOrder workOrder : getAllWorkOrders()) {
            if (workOrder != null)
                if (workOrder.getWorkOrderId() == id)
                    return workOrder;
        }
        return null;
    }

    @Override
    public ArrayList<WorkOrder> getAllWorkOrders() {
        ArrayList<WorkOrder> workOrders = new ArrayList<>();
        String sql = String.format("SELECT * from %s.workorders order by workorderid;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                workOrders.add(resultSetToWorkOrder(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return workOrders;
    }

    @Override
    public void update(WorkOrder workOrder) {
        ArrayList<WorkOrder> workOrders = new ArrayList<>();
        String sql = String.format("UPDATE %s.workorders set name=?, team=?, date=?, start_time=?, end_time=?, description=? where workorderid=?;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, workOrder.getName());
            statement.setString(2, workOrder.getTeam().getStringValue());
            statement.setDate(3, workOrder.getDateSQL());
            statement.setTime(4, workOrder.getStartTimeSQL());
            statement.setTime(5, workOrder.getEndTimeSQL());
            statement.setString(6, workOrder.getDescription());
            statement.setInt(7, workOrder.getWorkOrderId());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = String.format("DELETE from %s.workorders where workorderid=?;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public int getNumberOfWorkorders() {
        return getAllWorkOrders().size();
    }

    @Override
    public void checkOverlap(WorkOrder workOrder, Date date, LocalTime endTime) {
        ArrayList<WorkOrder> workOrders = getAllWorkOrders();
        for (WorkOrder wo : workOrders) {
            if (wo != null && wo.getWorkOrderId() != workOrder.getWorkOrderId())
                if (date.equals(wo.getDateSQL()))
                    if (endTime.isAfter(wo.getStartTime()) || endTime.equals(wo.getStartTime()))
                        if (endTime.isBefore(wo.getEndTime()) || endTime.equals(wo.getEndTime()))
                            throw new DbException("Workorder overlaps with other workorder(s)");
        }
    }

    @Override
    public void checkPast(Date date, LocalTime endTime) {
        if (date.equals(Date.valueOf(LocalDate.now())))
            if (endTime.isAfter(LocalTime.now()))
                throw new DbException("Workorder must be in the past");
        if (date.after(Date.valueOf(LocalDate.now())))
            throw new DbException("Workorder must be in the past");
    }

    @Override
    public ArrayList<WorkOrder> sortWorkOrders(String label, String order) {
        ArrayList<WorkOrder> workOrders = new ArrayList<>();
        int sort = 1;
        try {
            if (!label.isEmpty()) sort = Integer.parseInt(label);
        } catch (IllegalArgumentException ignored) {
        }
        String sql = String.format("SELECT * from %s.workorders order by date;", schema);
        if (order.trim().equals("desc")) sql = String.format("SELECT * from %s.workorders order by date desc;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                workOrders.add(resultSetToWorkOrder(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return workOrders;
    }

    public WorkOrder resultSetToWorkOrder(ResultSet result) throws SQLException {
        int id = result.getInt("workorderid");
        String name = result.getString("name").trim();
        String team = result.getString("team").trim();
        String date = result.getString("date").trim();
        String startTime = result.getString("start_time").trim();
        String endTime = result.getString("end_time").trim();
        String description = result.getString("description").trim();
        WorkOrder wo = new WorkOrder(id, name, Team.valueOf(team.toUpperCase()), LocalDate.parse(date), LocalTime.parse(startTime), LocalTime.parse(endTime), description);
        return wo;
    }

    private Connection getConnection() {
        return this.connection;
    }
}