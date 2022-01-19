package com.example.patterns.behavioral.observer.canonical;


public class Event {
    private final String name;

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
