package com.example.patterns.creational.singleton.v3;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Log4j2
public class SingletonEx {
    public static void main(String[] args) throws InterruptedException {
        log.info(Singleton.getInstance());
        log.info(Singleton.getInstance());

        log.info("Threads...");
        Thread threadFoo = new Thread(new ThreadFoo(Singleton.getInstance()));
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();

        threadFoo.join();
        threadBar.join();
        log.info("end");
    }
}

@Service
@Lazy
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Singleton {
    private static volatile Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

@Service
@Log4j2
class ThreadFoo implements Runnable {
    private final Singleton singleton;

    @Autowired
    public ThreadFoo(@Lazy Singleton singleton) {
        this.singleton = singleton;
    }

    @Override
    public void run() {
        log.info(singleton);
    }
}

@Service
@Log4j2
class ThreadBar implements Runnable {
    @Autowired
    @Lazy
    private Singleton singleton;

    @Override
    public void run() {
        log.info(singleton);
    }
}