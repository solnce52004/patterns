package com.example.patterns.structural.adapter.v1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Adapter {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        AtomicInteger counter = new AtomicInteger();
        Stream.of("af", "bdf", "c")
                .map(s -> new CharCounterTask(s, counter))
                .forEach(executor::execute);
//                .forEach(t -> new ExecutorConsumerAdaptor(executor).accept(t));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Total count: " + counter.get());
    }
}

class CharCounterTask implements Runnable {
    private final String str;
    private final AtomicInteger counter;

    public CharCounterTask(String str, AtomicInteger counter) {
        this.str = str;
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.addAndGet(str.length());
    }
}

//class ExecutorConsumerAdaptor implements Consumer<Runnable> {
//    private final Executor executor;
//
//    public ExecutorConsumerAdaptor(Executor executor) {
//        this.executor = executor;
//    }
//
//    @Override
//    public void accept(Runnable task) {
//        executor.execute(task);
//    }
//}