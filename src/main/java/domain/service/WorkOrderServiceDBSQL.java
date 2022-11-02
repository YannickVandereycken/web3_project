package domain.service;

import domain.model.Role;
import domain.model.Team;
import domain.model.User;
import domain.model.WorkOrder;
import util.DBConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            System.out.println(workOrder.getDateSQL());
            System.out.println(workOrder.getStartTime());
            System.out.println(workOrder.getEndTime());
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