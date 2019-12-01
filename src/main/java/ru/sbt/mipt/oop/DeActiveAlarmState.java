package ru.sbt.mipt.oop;


public class DeActiveAlarmState extends AlarmState {

    public DeActiveAlarmState(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void activate(String combination) {
        alarm.setAlarmCode(combination);
        alarm.setAlarmState(new ActiveAlarmState(this.alarm));
    }

    @Override
    public void deactivate(String combination) {

    }

    @Override
    public void setDangerMode() {
        alarm.setAlarmState(new DangerAlarmState(alarm));
    }


}
