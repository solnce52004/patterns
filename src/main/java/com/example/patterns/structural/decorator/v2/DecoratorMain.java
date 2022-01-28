package com.example.patterns.structural.decorator.v2;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DecoratorMain {
    public static void main(String[] args) {
        Developer developer = new JavaTeamLead(new SeniorJavaDeveloper(new JavaDeveloper()));
        developer.apply();
    }
}

interface Developer {
    void apply();
}

@Log4j2
class JavaDeveloper implements Developer {
    @Override
    public void apply() {
        log.info("Write Java code.");
    }
}

@Log4j2
@AllArgsConstructor
class DeveloperDecorator implements Developer {
    Developer developer;

    @Override
    public void apply() {
        developer.apply();
    }
}

@Log4j2
class JavaTeamLead extends DeveloperDecorator {
    public JavaTeamLead(Developer developer) {
        super(developer);
    }

    public void sendWeekReport() {
        log.info("Send week report to customer.");
    }

    @Override
    public void apply() {
        super.apply();
        sendWeekReport();
    }
}

@Log4j2
class SeniorJavaDeveloper extends DeveloperDecorator {
    public SeniorJavaDeveloper(Developer developer) {
        super(developer);
    }

    public void makeCodeReview() {
        log.info("Make code review.");
    }

    @Override
    public void apply() {
        super.apply();
        makeCodeReview();
    }
}
