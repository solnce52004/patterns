package com.example.patterns.creational.singleton.v1;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SingletonEx {
    public static void main(String[] args) throws InterruptedException {
        log.info(Singleton.getInstance(123).getValue());
        log.info(Singleton.getInstance(456).getValue());

        log.info("Threads...");
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();

        threadFoo.join();
        threadBar.join();
        log.info("end");
    }
}

@Getter
class Singleton {
    private static volatile Singleton instance;
    private final int value;

    private Singleton(int value) {
//         Этот код эмулирует медленную инициализацию.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    //Double-Checked Locking
    public static Singleton getInstance(int value) {
        //refactoring.guru
//        Singleton result = instance;
//        if (result != null) {
//            return result;
//        }

        //e.borisov
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton(value);
                }
            }
        }
        return instance;
    }
}

@Log4j2
class ThreadFoo implements Runnable {
    @Override
    public void run() {
        Singleton singleton = Singleton.getInstance(0);
        log.info(singleton.getValue());
    }
}

@Log4j2
class ThreadBar implements Runnable {
    @Override
    public void run() {
        Singleton singleton = Singleton.getInstance(1);
        log.info(singleton.getValue());
    }
}