package com.example.patterns.behavioral.memento.v1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public class Memento {
    public static void main(String[] args) throws InterruptedException {
        Project project = new Project();
        GithubRepo github = new GithubRepo();

        project.setVersion("1.0");
        log.info(project);

        log.info("Saving current version to github...");
        github.setSaver(project.createSaverByCurrentVersion());// промежуточное хранилище текущей версии проекта

        Thread.sleep(5000);
        project.setVersion("1.1.");
        log.info(project);

        project.setVersionFromSaver(github.getSaver());
        log.info("After rollback: {}", project);
    }
}

@Getter
@Setter
class GithubRepo {
    private Saver saver;
}

@ToString
class Project {
    private String version;
    private Date date;

    public void setVersion(String version) {
        this.version = version;
        this.date = new Date();
    }

    public Saver createSaverByCurrentVersion() {
        return new Saver(version);
    }

    public void setVersionFromSaver(Saver saver) {
        version = saver.getVersion();
        date = saver.getDate();
    }
}

@Getter
class Saver {
    private final String version;
    private final Date date;

    public Saver(String version) {
        this.version = version;
        this.date = new Date();
    }
}
