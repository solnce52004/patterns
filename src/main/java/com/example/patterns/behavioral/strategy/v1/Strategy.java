package com.example.patterns.behavioral.strategy.v1;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

public class Strategy {
    public static void main(String[] args) {
        Developer developer = new Developer();

        developer.setState(new Sleeping());
        developer.execute();

        developer.setState(new Coding());
        developer.execute();

        developer.setState(new Reading());
        developer.execute();
    }
}

interface State {
    DevState getName();

    void onChange();
}

class Developer {
    State state;

    public void setState(State state) {
        this.state = state;
    }

    public void execute() {
        state.onChange();
    }
}

@Log4j2
@Getter
class Coding implements State {
    private final DevState name = DevState.CODING;

    @Override
    public void onChange() {
        log.info("Coding");
    }
}

@Log4j2
@Getter
class Reading implements State {
    private final DevState name = DevState.READING;

    @Override
    public void onChange() {
        log.info("Reading");
    }
}

@Log4j2
@Getter
class Sleeping implements State {
    private final DevState name = DevState.SLEEPING;

    @Override
    public void onChange() {
        log.info("<<< Sleeping >>>");
    }
}

enum DevState {
    CODING, READING, SLEEPING
}