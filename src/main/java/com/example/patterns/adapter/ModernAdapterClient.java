package com.example.patterns.adapter;

import com.example.patterns.adapter.canonical.CharCounterTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class ModernAdapterClient {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        AtomicInteger counter = new AtomicInteger();
        Stream.of("af", "bdf", "c")
                .map(s -> new CharCounterTask(s, counter))
                .forEach(executor::execute);

        executor.shutdown();
        final boolean termination = executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Total count: " + counter.get());
    }
}
