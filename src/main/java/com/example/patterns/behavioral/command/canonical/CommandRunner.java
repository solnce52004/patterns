package com.example.patterns.behavioral.command.canonical;

import java.util.ArrayList;
import java.util.List;


public class CommandRunner {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command action) {
        commands.add(action);
    }

    public void run() {
        for (Command command : commands) {
            command.execute();
        }
    }
}
