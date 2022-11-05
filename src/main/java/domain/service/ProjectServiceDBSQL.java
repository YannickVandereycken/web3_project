package domain.service;

import domain.model.*;
import util.DBConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectServiceDBSQL implements ProjectService{

    private final Connection connection;
    private final String schema;

    public ProjectServiceDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(Project project) {
        String query = String.format("insert into %s.projects (name, team, startDate, endDate) values (?,?,?,?)", schema);
        try{
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,project.getName());
            statement.setString(2,project.getTeam().getStringValue());
            statement.setString(3, project.getStartDate().toString());
            statement.setString(4, project.getEndDate().toString());
            statement.execute();
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Project get(int userid) {
        for (Project project : getAllProjects()) {
            if (project != null)
                if (project.getProjectId() == userid)
                    return project;
        }
        return null;
    }

    @Override
    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        String sql = String.format("SELECT * from %s.projects order by projectid;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                projects.add(resultSetToProject(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return projects;
    }

    @Override
    public void update(Project project) {
        String query = String.format("update %s.projects set name=?, team=?, startDate=?, endDate=? where projectid=?", schema);
        try{
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,project.getName());
            statement.setString(2,project.getTeam().getStringValue());
            statement.setString(3, project.getStartDate().toString());
            statement.setString(4, project.getEndDate().toString());
            statement.setInt(5,project.getProjectId());
            statement.execute();
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(int projectId) {
        String sql = String.format("DELETE from %s.projects where projectid=?;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public int getNumberOfProjects() {
        return getAllProjects().size();
    }

    @Override
    public void checkUnique(String name, Team team){
        for (Project p : getAllProjects()){
            if(p.getName().equals(name) && p.getTeam().getStringValue().equals(team.getStringValue()))
                throw new DomainException("geen unieke combinatie van naam en team");
        }
    }

    public Project resultSetToProject(ResultSet result) throws SQLException{
        int id = result.getInt("userid");
        String name = result.getString("name").trim();
        String team = result.getString("team").trim();
        String startDate = result.getString("startDate").trim();
        String endDate = result.getString("endDate").trim();
        Project res = new Project(id, name, Team.valueOf(team.toUpperCase()), LocalDate.parse(startDate), LocalDate.parse(endDate));
        return res;
    }

    private Connection getConnection() {
        return this.connection;
    }
}
