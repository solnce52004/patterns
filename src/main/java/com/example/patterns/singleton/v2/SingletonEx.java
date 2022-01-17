package com.example.patterns.singleton.v2;

public class SingletonEx {
    public static void main(String[] args) {
        Singleton.INSTANCE.doWork();
    }
}

enum Singleton {
    INSTANCE;

    public void doWork() {
        System.out.println("Singleton working...");
    }
}