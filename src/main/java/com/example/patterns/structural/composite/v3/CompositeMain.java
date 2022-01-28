package com.example.patterns.structural.composite.v3;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

public class CompositeMain {
    public static void main(String[] args) {
        Team team = new Team();

        Developer firstJavaDeveloper = new JavaDeveloper();
        Developer secondJavaDeveloper = new JavaDeveloper();
        Developer cppDeveloper = new CppDeveloper();

        team.addDeveloper(firstJavaDeveloper);
        team.addDeveloper(secondJavaDeveloper);
        team.addDeveloper(cppDeveloper);
        team.createProject();

        team.removeDeveloper(secondJavaDeveloper);
        team.createProject();
    }
}

interface Developer {
    void writeCode();
}

@Log4j2
class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("Java developer writes Java code...");
    }
}
@Log4j2
class CppDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("C++ developer writes C++ code...");
    }
}
@Log4j2
class Team {
    private final List<Developer> developers = new ArrayList<>();

    public void addDeveloper(Developer developer) {
        developers.add(developer);
    }

    public void removeDeveloper(Developer developer) {
        developers.remove(developer);
    }

    public void createProject() {
        log.info("Team creates project...");
        for (Developer developer : developers) {
            developer.writeCode();
        }
    }
}
