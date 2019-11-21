package ru.sbt.mipt.oop;

public class DangerAlarmState extends AlarmState {

    public DangerAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String combination) {

    }

    @Override
    public void deactivate(String combination) {
        if(alarm.checkCode(combination)) {
            alarm.setAlarmState(new DeActiveAlarmState(alarm));
        }
    }

    @Override
    public void setDangerMode() {

    }

}
