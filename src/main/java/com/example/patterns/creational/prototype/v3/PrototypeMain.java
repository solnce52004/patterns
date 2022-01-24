package com.example.patterns.creational.prototype.v3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PrototypeMain {
    public static void main(String[] args) {
        Project master = new Project(1, "SuperProject", "SourceCode<...>");
        log.info(master);

        ProjectFactory factory = new ProjectFactory(master);
        Project masterClone = factory.cloneProject();
        log.info(masterClone);
    }
}

@AllArgsConstructor
@Setter
class ProjectFactory {
    Project project;

    Project cloneProject() {
        return project.copy();
    }
}

interface CopyableProject {
    Project copy();
}

@Data
@AllArgsConstructor
class Project implements CopyableProject {
    private int id;
    private String projectName;
    private String sourceCode;


    @Override
    public Project copy() {
        return new Project(id, projectName, sourceCode);
    }
}