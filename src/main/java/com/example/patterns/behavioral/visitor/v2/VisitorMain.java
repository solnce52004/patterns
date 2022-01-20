package com.example.patterns.behavioral.visitor.v2;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class VisitorMain {
    public static void main(String[] args) {
        Project project = new Project();

        //Developers are visitors
        log.info("Junior in Action...");
        project.beVisited(new JuniorVisitor());

        log.info("Senior in Action...");
        project.beVisited(new SeniorVisitor());
    }
}

interface ProjectElement {
    void beVisited(Visitor visitor);
}

//в недоступном классе должен быть метод-обертка
//с вызовом метода посетителя с реализацией для этого класса
class Test implements ProjectElement {
    @Override
    public void beVisited(Visitor visitor) {
        visitor.visit(this);
    }
}

class Database implements ProjectElement {
    @Override
    public void beVisited(Visitor visitor) {
        visitor.visit(this);
    }
}

class ProjectClass implements ProjectElement {
    @Override
    public void beVisited(Visitor visitor) {
        visitor.visit(this);
    }
}

class Project implements ProjectElement {
    ProjectElement[] projectElements;

    public Project() {
        this.projectElements = new ProjectElement[]{
                new ProjectClass(),
                new Database(),
                new Test()
        };
    }

    @Override
    public void beVisited(Visitor visitor) {
        for (ProjectElement element : projectElements) {
            element.beVisited(visitor);
        }
    }
}

interface Visitor {
    void visit(ProjectClass projectClass);
    void visit(Database database);
    void visit(Test test);
}

@Log4j2
class JuniorVisitor implements Visitor {
    @Override
    public void visit(ProjectClass projectClass) {
        log.info("Writing poor class");
    }

    @Override
    public void visit(Database database) {
        log.info("Drop database");
    }

    @Override
    public void visit(Test test) {
        log.info("Creating not reliable test");
    }
}

@Log4j2
class SeniorVisitor implements Visitor {
    @Override
    public void visit(ProjectClass projectClass) {
        log.info("Rewriting class after junior");
    }

    @Override
    public void visit(Database database) {
        log.info("Fixing database");
    }

    @Override
    public void visit(Test test) {
        log.info("Creating reliable test");
    }
}
