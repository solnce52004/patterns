package com.example.patterns.behavioral.templatemethod.canonical;


public class LoggingUserRestorer extends AbstractUserRestorer {
    @Override
    protected long store(User user) {
        System.out.println("User is stored: " + user);
        return System.currentTimeMillis();
    }
}
