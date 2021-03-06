package com.example.patterns.behavioral.command.modern;

import com.example.patterns.behavioral.command.canonical.Document;


public class ModernCommandClient {
    public static void main(String[] args) {
        Document document = new Document();
        ModernCommandRunner commandRunner = new ModernCommandRunner();

        commandRunner
                .addCommand(document::bold)
                .addCommand(document::italic)
                .addCommand(document::underline)
                .execute();
    }
}
