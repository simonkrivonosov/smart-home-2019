package ru.sbt.mipt.oop;

public class ActiveState extends State {

    public ActiveState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void Deactivate(String combination) {
        if(alarm.CheckCode(combination)) {
            alarm.deactivate();
        } else {
            alarm.alarm();
        }
    }

    @Override
    public void Activate(String combination) {

    }
}