package ru.sbt.mipt.oop;


public class Alarm {

    private String code;
    private AlarmState alarmState;


    public boolean checkCode(String combination) {
        return code.equals(combination);
    }

    public Alarm() {
        this.alarmState = new DeActiveAlarmState(this);
    }


    public void setAlarmCode(String initialCode) {
        this.code = initialCode;
    }

    public AlarmState getAlarmState() {
        return alarmState;
    }
    public void setAlarmState(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public void activate(String combination) {
        this.alarmState.activate(combination);
    }

    public void deactivate(String combination) {
        this.alarmState.deactivate(combination);
    }

    public void setDangerMode() {
        this.alarmState.setDangerMode();
    }

}
