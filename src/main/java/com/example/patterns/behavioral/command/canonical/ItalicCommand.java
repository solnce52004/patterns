package com.example.patterns.behavioral.command.canonical;


public class ItalicCommand implements Command {
    private final Editor editor;

    public ItalicCommand(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.italic();
    }
}

