package ru.sbt.mipt.oop;


public abstract class AlarmState {
    Alarm alarm;

    AlarmState(Alarm initAlarm) {
        this.alarm = initAlarm;
    }

    public abstract void activate(String combination);
    public abstract void deactivate(String combination);
    public abstract void setDangerMode();
}
