package com.example.patterns.observer.modern;

import com.example.patterns.observer.canonical.Event;

import java.util.function.Consumer;


public interface ModernObserver {
    void addListener(Consumer<Event> listener);
}
