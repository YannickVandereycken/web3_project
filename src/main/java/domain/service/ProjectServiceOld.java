package domain.service;

import domain.model.Project;
import domain.model.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectServiceOld {
    private final Map<Integer, Project> projects = new HashMap<Integer, Project>();
    private int projectId = 1;    // als je later werkt met externe databank, wordt dit projectid automatisch gegenereerd

    public ProjectServiceOld() {
        Project project1 = new Project("Project1", Team.ALPHA, LocalDate.now(), LocalDate.of(2023,1,1));
        add(project1);
        Project web3 = new Project("Web3", Team.BETA, LocalDate.now(), LocalDate.of(2022, 12, 23));
        add(web3);
        add(new Project("FoodLL", Team.GAMMA, LocalDate.now(), LocalDate.of(2022, 12,25)));
    }

    public Project get(int userid) {
        return projects.get(userid);
    }

    public List<Project> getAll() {
        return new ArrayList<Project>(projects.values());
    }

    public void add(Project project) {
        if (project == null) {
            throw new DbException("No user given");
        }
        if (projects.containsKey(project.getProjectId())) {
            throw new DbException("User already exists");
        }
        project.setProjectId(projectId);   // project toevoegen geeft altijd nieuw projectid
        projects.put(project.getProjectId(), project);
        projectId++;
    }

    public void update(Project project) {
        if (project == null) {
            throw new DbException("No user given");
        }
        if (!projects.containsKey(project.getProjectId())) {
            throw new DbException("No user found");
        }
        projects.put(project.getProjectId(), project); // project updaten: projectid blijft behouden
    }

    public void delete(int userid) {
        projects.remove(userid);   // projectid gaat verloren, maar wordt niet ingenomen door eventuele nieuw project
    }

    public int getNumberOfProjects() {
        return projects.size();
    }
}