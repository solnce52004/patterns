package com.example.patterns.creational.singleton.canonical;


public class SingletonClient implements NamedService {
    public static void main(String[] args) {
        new SingletonClient().start();
    }

    public void start() {
        ServiceRegistry.getInstance().register(this);
    }

    @Override
    public String getName() {
        return "MAIN";
    }
}
