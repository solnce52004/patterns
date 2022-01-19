package com.example.patterns.behavioral.observer.modern;

import com.example.patterns.behavioral.observer.canonical.Event;

import java.util.function.Consumer;


public interface ModernObserver {
    void addListener(Consumer<Event> listener);
}
