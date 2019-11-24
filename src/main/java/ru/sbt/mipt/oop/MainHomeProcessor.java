package ru.sbt.mipt.oop;

import java.util.Collection;

public class MainHomeProcessor implements EventProcessor {

    private Collection<EventProcessor> processors;

    public MainHomeProcessor(Collection<EventProcessor> eventProcessors) {
        this.processors = eventProcessors;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        for (EventProcessor processor : processors) {
            processor.processEvent(smartHome, sensorEvent);
        }

    }
}
