package com.example.patterns.command.modern;

import java.util.ArrayList;
import java.util.List;


public class ModernCommandRunner {
    private final List<Runnable> commands = new ArrayList<>();

    public ModernCommandRunner addCommand(Runnable cmd) {
        commands.add(cmd);
        return this;
    }

    public void execute() {
        commands.forEach(Runnable::run);
    }

//    public ModernCommandRunner addCommand(Command cmd) {
//        commands.add(cmd);
//        return this;
//    }
//
//    public void execute() {
//        commands.forEach(Command::execute);
//    }
}
