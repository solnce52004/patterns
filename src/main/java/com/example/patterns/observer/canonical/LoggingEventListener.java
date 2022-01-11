package com.example.patterns.observer.canonical;


public class LoggingEventListener implements EventListener {
    @Override
    public void onEvent(Event event) {
        System.out.println("EVENT: " + event.getName());
    }
}
