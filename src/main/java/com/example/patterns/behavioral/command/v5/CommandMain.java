package com.example.patterns.behavioral.command.v5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

public class CommandMain {
    public static void main(String[] args) {
        Database database = new Database();

        new Developer()
                .addCommand(new InsertCommand(database))
                .addCommand(new UpdateCommand(database))
                .addCommand(new SelectCommand(database))
                .addCommand(new DeleteCommand(database))
                .execute();
    }
}

@AllArgsConstructor
class Developer {
    private final List<Command> commands = new ArrayList<>();

    public Developer addCommand(Command cmd) {
        commands.add(cmd);
        return this;
    }

    public void execute() {
        commands.forEach(Command::execute);
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
@Getter
class DeleteCommand implements Command {
    private final Database database;

    @Override
    public void execute() {
        getDatabase().delete();
    }
}

@AllArgsConstructor
@Getter
class InsertCommand implements Command {
    private final Database database;

    @Override
    public void execute() {
        getDatabase().insert();
    }
}

@AllArgsConstructor
@Getter
class SelectCommand implements Command {
    private final Database database;

    @Override
    public void execute() {
        getDatabase().select();
    }
}

@AllArgsConstructor
@Getter
class UpdateCommand implements Command {
    private final Database database;

    @Override
    public void execute() {
        getDatabase().update();
    }
}
