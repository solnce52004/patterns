package com.example.patterns.behavioral.templatemethod.modern;

import com.example.patterns.behavioral.templatemethod.canonical.User;


public class LoggingUserStorage {
    public long store(User user) {
        System.out.println("User is stored: " + user);
        return System.currentTimeMillis();
    }
}
