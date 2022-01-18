package com.example.patterns.chain.v1;

import lombok.extern.log4j.Log4j2;

public class Chain {
    public static void main(String[] args) {
        Notifier info = new InfoNotifier(Priority.INFO);
        Notifier warning = new ErrorNotifier(Priority.WARNING);
        Notifier error = new WarningNotifier(Priority.ERROR);

        info.setNextNotifier(warning);
        warning.setNextNotifier(error);

        info.notify("Everything is OK.", Priority.INFO);
        info.notify("Something went wrong!", Priority.WARNING);
        info.notify("Houston, we've had a problem here!!!", Priority.ERROR);
    }
}

abstract class Notifier {
    private final int priority;
    private Notifier nextNotifier;

    public Notifier(int priority) {
        this.priority = priority;
    }

    public void setNextNotifier(Notifier nextNotifier) {
        this.nextNotifier = nextNotifier;
    }

    public void notify(String message, int level) {
        if (level >= priority) {
            send(message);
        }
        if (nextNotifier != null) {
            nextNotifier.notify(message, level);
        }
    }

    public abstract void send(String message);
}

class Priority {
    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
}

@Log4j2
class InfoNotifier extends Notifier {
    public InfoNotifier(int priority) {
        super(priority);
    }

    @Override
    public void send(String message) {
        log.info("INFO: Notifying using INFO report: " + message);
    }
}

@Log4j2
class WarningNotifier extends Notifier {
    public WarningNotifier(int priority) {
        super(priority);
    }

    @Override
    public void send(String message) {
        log.warn("WARNING: Sending SMS to manager:" + message);
    }
}

@Log4j2
class ErrorNotifier extends Notifier {
    public ErrorNotifier(int priority) {
        super(priority);
    }

    @Override
    public void send(String message) {
        log.error("ERROR: Sending email: " + message);
    }
}
