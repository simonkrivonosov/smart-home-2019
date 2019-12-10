package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.EventProcessor;
import ru.sbt.mipt.oop.adapter.CCSensorEventConverters.CCSensorEventConverter;


public class ProcessorAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventProcessor processor;
    private final SmartHome smartHome;
    private final CCSensorEventConverter eventAdapter;

    public ProcessorAdapter(EventProcessor processor, SmartHome smartHome, CCSensorEventConverter eventAdapter) {
        this.processor = processor;
        this.smartHome = smartHome;
        this.eventAdapter = eventAdapter;
    }

    public void handleEvent(CCSensorEvent event) {
        processor.processEvent(smartHome, eventAdapter.convert(event));
    }

}
