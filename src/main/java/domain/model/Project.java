package domain.model;

import java.sql.Date;
import java.time.LocalDate;

public class Project {
    private int projectId;
    private String name;
    private Team team;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(String name, Team team, LocalDate startDate, LocalDate endDate) {
        setName(name);
        setTeam(team);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Project(int projectId, String name, Team team, LocalDate startDate, LocalDate endDate){
        setProjectId(projectId);
        setName(name);
        setTeam(team);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeam(String team) {
        try {
            this.team = Team.valueOf(team.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DomainException("There is no team with value " + team);
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Date getStartDateSQL() {
        return Date.valueOf(startDate);
    }

    public void setStartDate(LocalDate startDate) {
        if(this.endDate!=null && this.endDate.isBefore(startDate))
            throw new DomainException("The startdate needs to be after the end date");
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Date getEndDateSQL() {
        return Date.valueOf(endDate);
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate.isBefore(this.startDate))
            throw new DomainException("The end date needs to be after the start date");
        this.endDate = endDate;
    }
}