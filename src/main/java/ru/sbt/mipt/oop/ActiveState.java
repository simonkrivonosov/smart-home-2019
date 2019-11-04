package ru.sbt.mipt.oop;

public class ActiveState extends State {

    public ActiveState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void deactivate(String combination) {
        if(alarm.CheckCode(combination)) {
            alarm.deactivate();
        } else {
            alarm.alarm();
        }
    }

    @Override
    public void activate(String combination) {

    }
}