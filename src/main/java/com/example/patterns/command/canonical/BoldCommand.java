package com.example.patterns.command.canonical;


public class BoldCommand implements Command {
    private final Editor editor;

    public BoldCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.bold();
    }
}
