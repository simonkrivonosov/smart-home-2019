package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Alarm;

public abstract class State {
    Alarm alarm;

    State(Alarm init_alarm) {
        this.alarm = init_alarm;
    }

    public abstract void activate(String combination);
    public abstract void deactivate(String combination);
}
