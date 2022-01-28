package com.example.patterns.structural.flyweight.v3;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flyweight {
    public static void main(String[] args) {
        //фабрика кэширует виды объектов - Приспособленец
        DeveloperFactory factory = new DeveloperFactory();
        List<Developer> developers = new ArrayList<>();

        //при добавлении новых элементов используется мапа фабрики
        developers.add(factory.getDeveloper("java"));
        developers.add(factory.getDeveloper("java"));
        developers.add(factory.getDeveloper("c++"));
        developers.add(factory.getDeveloper("c++"));
        developers.add(factory.getDeveloper("c"));

        for (Developer developer : developers) {
            developer.writeCode();
        }
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
class DefaultDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("Default developer writes code...");
    }
}

@Log4j2
class DeveloperFactory {
    private static final Map<String, Developer> developers = new HashMap<>();

    public Developer getDeveloper(String specialty) {
        Developer developer = developers.get(specialty);

        if (developer == null) {
            switch (specialty) {
                case "java":
                    log.info("Hiring Java developer...");
                    developer = new JavaDeveloper();
                    break;
                case "c++":
                    log.info("Hiring C++ developer...");
                    developer = new CppDeveloper();
                    break;
                default:
                    log.info("Hiring Default developer...");
                    developer = new DefaultDeveloper();
                    break;
            }
            developers.put(specialty, developer);
        }
        return developer;
    }
}
