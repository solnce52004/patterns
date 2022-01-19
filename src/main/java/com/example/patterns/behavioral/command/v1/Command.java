package com.example.patterns.behavioral.command.v1;

import java.util.ArrayList;
import java.util.List;

public class Command {
    public static void main(String[] args) {
        Document document = new Document();
        new CommandRunner()
                .addCommand(document::bold)
                .addCommand(document::italic)
                .execute();
    }
}

class CommandRunner {
    private final List<Runnable> commands = new ArrayList<>();

    public CommandRunner addCommand(Runnable cmd) {
        commands.add(cmd);
        return this;
    }

    public void execute() {
        commands.forEach(Runnable::run);
    }
}

class Document {
    public void bold() {
        System.out.println("Bold text...");
    }

    public void italic() {
        System.out.println("Italic text...");
    }
}