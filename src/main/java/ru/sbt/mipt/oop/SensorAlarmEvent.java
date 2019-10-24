package ru.sbt.mipt.oop;



public class SensorAlarmEvent extends SensorEvent {
    private String code;

    public SensorAlarmEvent(SensorEventType type, String code) {
        super(type, "-1");
        this.code = code;
    }

    public String getCode() {return code;}
}
