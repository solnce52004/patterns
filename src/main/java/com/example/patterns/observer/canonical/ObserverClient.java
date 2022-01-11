package com.example.patterns.observer.canonical;


public class ObserverClient {
    public static void main(String[] args) {
        TimeServer server = new TimeServer();
        server.addListener(new LoggingEventListener());
        server.reportTime();
        server.reportTime();
    }
}
