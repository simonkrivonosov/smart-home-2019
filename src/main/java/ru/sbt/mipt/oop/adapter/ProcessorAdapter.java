package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.EventProcessor;


public class ProcessorAdapter implements com.coolcompany.smarthome.events.EventHandler {
    EventProcessor processor;
    SmartHome smartHome;

    public ProcessorAdapter(EventProcessor processor, SmartHome smartHome) {
        this.processor = processor;
        this.smartHome = smartHome;
    }

    public void handleEvent(CCSensorEvent event) {
        processor.processEvent(smartHome, (new EventAdapter(event)).getAdaptEvent());
    }

}
