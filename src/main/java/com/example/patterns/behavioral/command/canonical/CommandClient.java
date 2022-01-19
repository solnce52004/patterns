package com.example.patterns.behavioral.command.canonical;


public class CommandClient {
    public static void main(String[] args) {
        Document editor = new Document();

        CommandRunner commandRunner = new CommandRunner();
        commandRunner.addCommand(new BoldCommand(editor));
        commandRunner.addCommand(new ItalicCommand(editor));
        commandRunner.addCommand(new UnderlineCommand(editor));
        commandRunner.run();
    }
}
