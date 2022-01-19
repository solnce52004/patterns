package com.example.patterns.behavioral.command.canonical;


public class UnderlineCommand implements Command {
    private final Editor editor;

    public UnderlineCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.underline();
    }
}
