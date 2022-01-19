package com.example.patterns.behavioral.iterator.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class IteratorMain {
    public static void main(String[] args) {
        String[] skills = {"Java", "Spring Boot", "Hibernate", "Gradle", "MySQL"};
        JavaDeveloper javaDeveloper = new JavaDeveloper("Ivanov", skills);
        Iterator iterator = javaDeveloper.getIterator();
        log.info("Developer: " + javaDeveloper.getName());

        while (iterator.hasNext()) {
            log.info(iterator.next().toString());
        }
    }
}

interface Iterator {
    boolean hasNext();
    Object next();
}

interface Collection {
    Iterator getIterator();
}

@AllArgsConstructor
@Getter
class JavaDeveloper implements Collection {
    private final String name;
    private final String[] skills;

    @Override
    public Iterator getIterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            return index < getSkills().length;
        }

        @Override
        public Object next() {
            return getSkills()[index++];
        }
    }
}
