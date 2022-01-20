package com.example.patterns.behavioral.observer.v2;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

public class ObserverMain {
    public static void main(String[] args) {
        HeadHunter hh = new HeadHunter();
        hh.addVacancy("junior");
        hh.addVacancy("middle");

        EventListener first = new HHEventListener("Eugene Suleimanov");
        EventListener second = new HHEventListener("Peter Romanenko");
        hh.subscribe(first);
        hh.subscribe(second);

        hh.addVacancy("senior");//есть подписчики - отработает авто-уведомление при изменениях
        hh.unsubscribe(second);
        hh.removeVacancy("junior");
    }
}

interface EventListener {
    void onEvent(List<String> vacancies);
}

interface EventManager {
    void subscribe(EventListener listener);
    void unsubscribe(EventListener listener);
    void notifyObserver();
}

@Log4j2
@Getter
@Setter
@AllArgsConstructor
class HHEventListener implements EventListener {
    private static final String MESSAGE = "{}, vacancies: {}";
    private final String name;

    @Override
    public void onEvent(List<String> vacancies) {
        log.info(MESSAGE, getName(), vacancies);
    }
}

class HeadHunter implements EventManager {
    List<String> vacancies = new ArrayList<>();
    List<EventListener> listeners = new ArrayList<>();

    public void addVacancy(String vacancy) {
        this.vacancies.add(vacancy);
        notifyObserver();
    }

    public void removeVacancy(String vacancy) {
        this.vacancies.remove(vacancy);
        notifyObserver();
    }

    @Override
    public void subscribe(EventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void unsubscribe(EventListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void notifyObserver() {
        for (EventListener listener : listeners) {
            listener.onEvent(this.vacancies);
        }
    }
}