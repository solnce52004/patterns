package com.example.patterns.command.canonical;


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

