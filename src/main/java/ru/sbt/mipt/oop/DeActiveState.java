package ru.sbt.mipt.oop;


public class DeActiveState extends State{

    public DeActiveState(Alarm alarm) {
        super(alarm);

    }

    @Override
    public void activate(String combination) {
        if (alarm.CheckCode(combination)) {
            alarm.activate();
        }
    }

    @Override
    public void deactivate(String combination) {

    }




}
