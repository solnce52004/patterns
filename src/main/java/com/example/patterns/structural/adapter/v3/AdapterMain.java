package com.example.patterns.structural.adapter.v3;

import lombok.extern.log4j.Log4j2;

public class AdapterMain {
    public static void main(String[] args) {
        Database database = new AdapterJavaToDatabase();
        database.insert();
        database.update();
        database.select();
        database.remove();
    }
}

interface Database {
    void insert();
    void update();
    void select();
    void remove();
}

@Log4j2
class JavaApplication {
    public void saveObject() {
        log.info("Saving Java object...");
    }

    public void updateObject() {
        log.info("Updating Java object...");
    }

    public void loadObject() {
        log.info("Loading Java object...");
    }

    public void deleteObject() {
        log.info("Deleting Java object...");
    }
}

class AdapterJavaToDatabase extends JavaApplication implements Database {
    @Override
    public void insert() {
        saveObject();
    }

    @Override
    public void update() {
        updateObject();
    }

    @Override
    public void select() {
        loadObject();
    }

    @Override
    public void remove() {
        deleteObject();
    }
}
