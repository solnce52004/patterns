package com.example.patterns.behavioral.visitor.v1;

public interface Developer {
    public void create(ProjectClass projectClass);

    public void create(Database database);

    public void create(Test test);
}
