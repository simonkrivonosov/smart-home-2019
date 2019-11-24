package ru.sbt.mipt.oop;

import java.util.Collection;

public class SmartHomeEventObserver {

    private SmartHome smarthome;
    private EventGenerator eventGenerator;
    private EventProcessor eventProcessor;

    public SmartHomeEventObserver(SmartHome smarthome, EventGenerator eventGenerator, EventProcessor eventProcessor) {
        this.smarthome = smarthome;
        this.eventProcessor = eventProcessor;
        this.eventGenerator = eventGenerator;
    }

    public void observe() {
        SensorEvent event = eventGenerator.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = eventGenerator.getNextSensorEvent();
        }
    }
    public void processEvent(SensorEvent sensorEvent) {
        System.out.println("Got event: " + sensorEvent);
        eventProcessor.processEvent(smarthome, sensorEvent);
    }
}
