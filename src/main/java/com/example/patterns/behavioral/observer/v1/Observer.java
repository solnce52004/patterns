package com.example.patterns.behavioral.observer.v1;

import java.util.List;

public interface Observer {
    public void handleEvent(List<String> vacancies);
}
