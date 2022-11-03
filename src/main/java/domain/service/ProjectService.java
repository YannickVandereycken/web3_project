package domain.service;

import domain.model.Project;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public interface ProjectService {
    Project get(int userid);

    ArrayList<Project> getAllProjects();

    void add(Project project);

    void update(Project project);

    void delete(int projectId);

    int getNumberOfProjects();
}
