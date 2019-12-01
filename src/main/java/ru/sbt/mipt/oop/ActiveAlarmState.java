package ru.sbt.mipt.oop;

public class ActiveAlarmState extends AlarmState {

    public ActiveAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void deactivate(String combination) {
        if(alarm.checkCode(combination)) {
            alarm.setAlarmState(new DeActiveAlarmState(alarm));
        } else {
            alarm.setAlarmState(new DangerAlarmState(alarm));
        }
    }

    @Override
    public void setDangerMode() {
        alarm.setAlarmState(new DangerAlarmState(alarm));
    }

    @Override
    public void activate(String combination) {

    }
}