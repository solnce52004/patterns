package com.example.patterns.structural.adapter.canonical;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class AdapterClient {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        AtomicInteger counter = new AtomicInteger();
        Stream.of("af", "bdf", "c")
                .map(s -> new CharCounterTask(s, counter))
                .forEach(new ExecutorConsumerAdaptor(executor));
//                .forEach(t -> new ExecutorConsumerAdaptor(executor).accept(t));

        executor.shutdown();
        final boolean termination = executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Total count: " + counter.get());
    }
}
