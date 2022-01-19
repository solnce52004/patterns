package com.example.patterns.creational.factorymethod.canonical;

public interface Document {
    String getName();

    void addField(String name, String value);

    String toString();
}
