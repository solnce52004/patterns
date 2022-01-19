package com.example.patterns.behavioral.state.v2;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

public class StateMain {
    public static void main(String[] args) {
        State state = new Sleeping();
        Developer developer = new Developer();
        developer.setState(state);

        for (int i = 0; i < 10; i++) {
            developer.execute();
            developer.changeActivity();
        }
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

    public void changeActivity() {
        switch (state.getName()) {
            case SLEEPING:
                setState(new Coding());
                break;
            case CODING:
                setState(new Reading());
                break;
            case READING:
                setState(new Sleeping());
                break;
        }
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