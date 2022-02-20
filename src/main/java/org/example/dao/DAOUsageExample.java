package org.example.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DAOUsageExample {
    public static void main(String[] args) {

        // połączenie z bazą danych
        ProjectService projectService = new ProjectService(new ProjectDAODB());

        // dodaje nowy project z losowym id
        int randId = (new Random()).nextInt(99);
        Project project = new Project("Project #" + randId, new Date());

        projectService.persist(project);

        // wyswietla wszystkie projekty
        List<Project> projects = projectService.findAll();
        projects.stream().forEach(p -> System.out.println(p));

        // wyświetla pierszy project z bazy danych
        Project projectFromDB = projectService.findById(projects.get(0).getId());
        System.out.println("\nProject from DB:" + projectFromDB);

        projectService.delete(projectFromDB);
        System.out.println("Project id: " + projectFromDB.getId() + " deleted");

        System.out.println("\nProject list after delete:");
        projects = projectService.findAll();
        projects.stream().forEach(p -> System.out.println(p));

    }
}
