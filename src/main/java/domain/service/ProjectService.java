package domain.service;

import domain.model.Project;
import domain.model.Team;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ProjectService {
    Project get(int userid);

    ArrayList<Project> getAllProjects();

    void add(Project project);

    ArrayList<Project> getProjectsOfTeam(Team team);

    void update(Project project);

    void delete(int projectId);

    int getNumberOfProjects();

    void checkUnique(String name, Team team);

    ArrayList<Project> find(LocalDate date);

    ArrayList<Project> findOfTeam(LocalDate date, Team team);

    ArrayList<Project> sort(String order);

    ArrayList<Project> sortOfTeam(Team team, String order);
}