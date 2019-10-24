package ru.sbt.mipt.oop;


public class Alarm {
    private String code;
    private State state;
    boolean is_active;


    public boolean CheckCode(String combination) {
        return code.equals(combination);
    }

    public Alarm() {
        code = "0000";
        state = new DeActiveState(this);
        is_active = false;
    }

    public Alarm(String initial_code) {
        code = initial_code;
        state = new DeActiveState(this);
        is_active = false;
    }

    public State getState() {
        return state;
    }

    public void activate() {
        state = new ActiveState(this);
        is_active = true;
    }

    public void deactivate() {
        state = new DeActiveState(this);
        is_active = false;
    }

    public void alarm() {
        state = new AlarmState(this);
        is_active = true;
    }

}
