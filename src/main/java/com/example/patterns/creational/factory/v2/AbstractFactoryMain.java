package com.example.patterns.creational.factory.v2;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AbstractFactoryMain {
    public static void main(String[] args) {
        //website
        ProjectTeamFactory webFactory = new WebsiteTeamFactory();
        Developer webTeamLead = webFactory.getFirstDeveloper();
        Developer webDeveloper = webFactory.getSecondDeveloper();
        Tester webTester = webFactory.getTester();
        ProjectManger webPM = webFactory.getProjectManager();
        log.info("------- Creating website project -------");
        webTeamLead.writeCode();
        webDeveloper.writeCode();
        webTester.test();
        webPM.manage();

        // bank
        ProjectTeamFactory bankFactory = new BankTeamFactory();
        Developer bankTeamLead = bankFactory.getFirstDeveloper();
        Developer bankDeveloper = bankFactory.getSecondDeveloper();
        Tester bankTester = bankFactory.getTester();
        ProjectManger bankPM = bankFactory.getProjectManager();
        log.info("------- banking project --------");
        bankTeamLead.writeCode();
        bankDeveloper.writeCode();
        bankTester.test();
        bankPM.manage();
    }
}

interface ProjectTeamFactory {
    Developer getFirstDeveloper();
    Developer getSecondDeveloper();
    Tester getTester();
    ProjectManger getProjectManager();
}

interface Developer {
    void writeCode();
}

interface Tester {
    void test();
}

interface ProjectManger {
    void manage();
}

//--------website---------------------
@Log4j2
class PhpDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("Php developer writes php code...");
    }
}

@Log4j2
class ManualTester implements Tester {
    @Override
    public void test() {
        log.info("Manual tester tests website...");
    }
}

@Log4j2
class WebsitePM implements ProjectManger {
    @Override
    public void manage() {
        log.info("Website PM manages website project...");
    }
}

class WebsiteTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getFirstDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Developer getSecondDeveloper() {
        return new PhpDeveloper();
    }

    @Override
    public Tester getTester() {
        return new ManualTester();
    }

    @Override
    public ProjectManger getProjectManager() {
        return new WebsitePM();
    }
}

//--------banking---------------------
@Log4j2
class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        log.info("Java developer writes Java code...");
    }
}

@Log4j2
class QATester implements Tester {
    @Override
    public void test() {
        log.info("QA tester tests banking project...");
    }
}

@Log4j2
class BankingPM implements ProjectManger {
    @Override
    public void manage() {
        log.info("Banking PM manages banking project...");
    }
}

class BankTeamFactory implements ProjectTeamFactory {
    @Override
    public Developer getFirstDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Developer getSecondDeveloper() {
        return new JavaDeveloper();
    }

    @Override
    public Tester getTester() {
        return new QATester();
    }

    @Override
    public ProjectManger getProjectManager() {
        return new BankingPM();
    }
}



