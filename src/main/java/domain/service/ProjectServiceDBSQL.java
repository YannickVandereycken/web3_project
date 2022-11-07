package domain.service;

import domain.model.*;
import util.DBConnectionService;

import java.sql.*;
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
        String query = String.format("insert into %s.projects (name, team, start_date, end_date) values (?,?,?,?)", schema);
        try{
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,project.getName());
            statement.setString(2,project.getTeam().getStringValue());
            statement.setDate(3, project.getStartDateSQL());
            statement.setDate(4, project.getEndDateSQL());
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
        String query = String.format("update %s.projects set name=?, team=?, start_date=?, end_date=? where projectid=?", schema);
        try{
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,project.getName());
            statement.setString(2,project.getTeam().getStringValue());
            statement.setDate(3, project.getStartDateSQL());
            statement.setDate(4, project.getEndDateSQL());
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
        if(name.isEmpty())
            throw new DomainException("please fill in a name");
        for (Project p : getAllProjects()){
            if(p.getName().equals(name) && p.getTeam().getStringValue().equals(team.getStringValue()))
                throw new DomainException("not a unique combination of name and team");
        }
    }

    @Override
    public ArrayList<Project> find(LocalDate date){
        ArrayList<Project> result = new ArrayList<>();
        for (Project project : getAllProjects()){
            if (project != null)
                if( (project.getStartDate().isBefore(date) && project.getEndDate().isAfter(date)) || project.getEndDate().isEqual(date) || project.getStartDate().isEqual(date))
                    result.add(project);
        }
        if(result.isEmpty())
            throw new DbException("Op de ingevulde datum zijn er geen projecten bezig");
        return result;
    }

    public Project resultSetToProject(ResultSet result) throws SQLException{
        int id = result.getInt("projectid");
        String name = result.getString("name").trim();
        String team = result.getString("team").trim();
        String startDate = result.getString("start_date").trim();
        String endDate = result.getString("end_date").trim();
        Project res = new Project(id, name, Team.valueOf(team.toUpperCase()), LocalDate.parse(startDate), LocalDate.parse(endDate));
        return res;
    }

    private Connection getConnection() {
        return this.connection;
    }
}
