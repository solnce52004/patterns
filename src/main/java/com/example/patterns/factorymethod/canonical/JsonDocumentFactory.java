package com.example.patterns.factorymethod.canonical;

public class JsonDocumentFactory implements DocumentFactory {
    @Override
    public Document create(String name) {
        return new JsonDocument(name);
    }
}
