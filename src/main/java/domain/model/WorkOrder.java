package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class WorkOrder {

    private int workOrderId;
    private String name;
    private Team team;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    public WorkOrder(String name, Team team, LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
        setName(name);
        setTeam(team);
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        setDescription(description);
    }

    public WorkOrder() {

    }

    public int getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (endTime.isBefore(startTime)) throw new DomainException("Endtime must be after starttime");
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 100) throw new DomainException("Max length of 100 characters");
        this.description = description;
    }

    public int getTotalTime() {
        return (int) startTime.until(endTime, ChronoUnit.MINUTES);
    }
}