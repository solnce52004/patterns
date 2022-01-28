package com.example.patterns.structural.facade.v2;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

public class FacadeMain {
    public static void main(String[] args) {
        WorkflowFacade facade = new WorkflowFacade();
        facade.solveSprintProblems();
        facade.solvePlainProblems();
    }
}

//унифицированный интерфейс вместо нескольких интерфейсов подсистемы
class WorkflowFacade {
    Developer developer = new Developer();
    Job job = new Job();
    BugTracker bugTracker = new BugTracker();

    public void solveSprintProblems() {
        job.proceed();
        bugTracker.startSprint();
        developer.doJobBeforeDeadline(bugTracker);
    }

    public void solvePlainProblems() {
        job.proceed();
        bugTracker.finishSprint();
        developer.doJobBeforeDeadline(bugTracker);
    }
}

@Log4j2
class Developer {
    public void doJobBeforeDeadline(BugTracker bugTracker) {
        log.info(bugTracker.isActiveSprint()
                        ? "Developer is solving problems..."
                        : "Developer is reading Habrahabr...");
    }
}

@Log4j2
class Job {
    public void proceed() {
        log.info("Job in progress...");
    }
}

@Log4j2
@Getter
class BugTracker {
    private boolean activeSprint;

    public void startSprint() {
        activeSprint = true;
        log.info("Sprint is active.");
    }

    public void finishSprint() {
        activeSprint = false;
        log.info("Sprint is not active.");
    }
}
