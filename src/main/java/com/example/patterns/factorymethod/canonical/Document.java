package com.example.patterns.factorymethod.canonical;

public interface Document {
    String getName();

    void addField(String name, String value);

    String toString();
}
