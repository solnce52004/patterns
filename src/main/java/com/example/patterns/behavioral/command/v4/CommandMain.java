package com.example.patterns.behavioral.command.v4;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

public class CommandMain {
    public static void main(String[] args) {
        Database database = new Database();

        Developer developer = new Developer(
                new InsertCommand(database),
                new UpdateCommand(database),
                new SelectCommand(database),
                new DeleteCommand(database)
        );

        developer.insertRecord();
        developer.updateRecord();
        developer.selectRecord();
        developer.deleteRecord();
    }
}

@AllArgsConstructor
class Developer {
    Command insert;
    Command update;
    Command select;
    Command delete;

    public void insertRecord() {
        insert.execute();
    }

    public void updateRecord() {
        update.execute();
    }

    public void selectRecord() {
        select.execute();
    }

    public void deleteRecord() {
        delete.execute();
    }
}

@Log4j2
class Database {
    public void insert() {
        log.info("Inserting");
    }

    public void update() {
        log.info("Updating");
    }

    public void select() {
        log.info("Reading");
    }

    public void delete() {
        log.info("Deleting");
    }
}

interface Command {
    void execute();
}

@AllArgsConstructor
class DeleteCommand implements Command {
    Database database;

    @Override
    public void execute() {
        database.delete();
    }
}

@AllArgsConstructor
class InsertCommand implements Command {
    Database database;

    @Override
    public void execute() {
        database.insert();
    }
}

@AllArgsConstructor
class SelectCommand implements Command {
    Database database;

    @Override
    public void execute() {
        database.select();
    }
}

@AllArgsConstructor
class UpdateCommand implements Command {
    Database database;

    @Override
    public void execute() {
        database.update();
    }
}
