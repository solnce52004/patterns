package com.example.patterns.structural.proxy.v3;

import lombok.extern.log4j.Log4j2;

public class ProxyMain {
    public static void main(String[] args) {
        Project proxy = new ProxyProject("https://www.github.com/someuser/realProject");
        proxy.run();
        proxy.run();
    }
}

interface Project {
    void run();
}

@Log4j2
class RealProject implements Project {
    private final String url;

    public RealProject(String url) {
        this.url = url;
        load();
    }

    public void load() {
        log.info("Loading project from {}...", url);
    }

    @Override
    public void run() {
        log.info("Running project {}...", url);
    }
}

class ProxyProject implements Project {
    private final String url;
    private RealProject realProject;

    public ProxyProject(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        if (realProject == null) {
            realProject = new RealProject(url);
        }
        realProject.run();
    }
}
