package ru.sbt.mipt.oop;

import java.util.Collection;

public class SmartHomeEventObserver {

    private SmartHome smarthome;
    private EventGenerator eventGenerator;
    private Collection<EventProcessor> eventProcessors;

    public SmartHomeEventObserver(SmartHome smarthome, EventGenerator eventGenerator, Collection<EventProcessor> eventProcessors ) {
        this.smarthome = smarthome;
        this.eventProcessors = eventProcessors;
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
        for (EventProcessor eventProcessor : eventProcessors) {
            eventProcessor.processEvent(smarthome, sensorEvent);
        }
    }
}
