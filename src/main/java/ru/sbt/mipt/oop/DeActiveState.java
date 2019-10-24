package ru.sbt.mipt.oop;


public class DeActiveState extends State{

    public DeActiveState(Alarm alarm) {
        super(alarm);

    }

    @Override
    public void Activate(String combination) {
        if (alarm.CheckCode(combination)) {
            alarm.activate();
        }
    }

    @Override
    public void Deactivate(String combination) {

    }




}
