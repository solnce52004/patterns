package com.example.patterns.behavioral.chain.v1;

import lombok.extern.log4j.Log4j2;

public class Chain {
    public static void main(String[] args) {
        Notifier info = new InfoNotifier(Level.INFO);
        Notifier warning = new WarningNotifier(Level.WARNING);
        Notifier error = new ErrorNotifier(Level.ERROR);

        info
                .setNextNotifier(warning)
                .setNextNotifier(error);

//        info.notify("event msg", Level.DEBUG);
        info.notify("event msg", Level.WARNING);
//        info.notify("event msg", Level.ERROR);
    }
}

abstract class Notifier {
    private final int level;
    private Notifier nextNotifier;

    public Notifier(int level) {
        this.level = level;
    }

    public Notifier setNextNotifier(Notifier nextNotifier) {
        this.nextNotifier = nextNotifier;
        return nextNotifier;
    }

    public void notify(String message, int msgLevel) {
        if (level >= msgLevel) {
            send(message);
            if (nextNotifier != null) {
                nextNotifier.notify(message, msgLevel);
            }
        }
    }

    public abstract void send(String message);
}

class Level {
    public static final int INFO = 400;
    public static final int WARNING = 300;
    public static final int ERROR = 200;
}

@Log4j2
class InfoNotifier extends Notifier {
    public InfoNotifier(int level) {
        super(level);
    }

    @Override
    public void send(String message) {
        log.info("INFO: Notifying using console: " + message);
    }
}

@Log4j2
class WarningNotifier extends Notifier {
    public WarningNotifier(int level) {
        super(level);
    }

    @Override
    public void send(String message) {
        log.warn("WARNING: Sending SMS to manager: " + message);
    }
}

@Log4j2
class ErrorNotifier extends Notifier {
    public ErrorNotifier(int level) {
        super(level);
    }

    @Override
    public void send(String message) {
        log.error("ERROR: Sending email to admin: " + message);
    }
}