package com.example.patterns.behavioral.chain.modern;

import com.example.patterns.behavioral.chain.canonical.Request;

import java.util.function.Consumer;


public class ModernAuthRequestHandler implements Consumer<Request> {
    private static final ThreadLocal<Long> currentUser = new ThreadLocal<>();

    @Override
    public void accept(Request request) {
        currentUser.set(request.getUserId());
        System.out.println("Current user is set to: " + request.getUserId());
        request.addParam("AUTH_PASSED", "TRUE");
    }

    public static Long getCurrentUser() {
        return currentUser.get();
    }
}
