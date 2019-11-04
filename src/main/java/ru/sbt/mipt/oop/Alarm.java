package ru.sbt.mipt.oop;


public class Alarm {
    private String code;
    private State state;
    boolean isActive;


    public boolean CheckCode(String combination) {
        return code.equals(combination);
    }

    public Alarm() {
        code = "0000";
        state = new DeActiveState(this);
        isActive = false;
    }

    public Alarm(String initialCode) {
        code = initialCode;
        state = new DeActiveState(this);
        isActive = false;
    }

    public State getState() {
        return state;
    }

    public void activate() {
        state = new ActiveState(this);
        isActive = true;
    }

    public void deactivate() {
        state = new DeActiveState(this);
        isActive = false;
    }

    public void alarm() {
        state = new AlarmState(this);
        isActive = true;
    }

}
