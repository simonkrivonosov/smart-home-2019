package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new JsonSmartHomeReader("smart-home-1.js");
        EventGenerator eventGenerator = new RandomEventGenerator();
        CommandSender commandSender = new TextCommandSender();
        SmartHome smartHome = smartHomeReader.loadSmartHome();
        smartHome.setAlarm(new Alarm());
        EventProcessor mainHomeProcessor = new MainHomeProcessor(createEventProcessors());
        SmartHomeEventObserver smartHomeEventObserver = new SmartHomeEventObserver(smartHome, eventGenerator, new EventProcessorDecorator(mainHomeProcessor));
        smartHomeEventObserver.observe();
    }


    private static Collection<EventProcessor> createEventProcessors() {
        Collection<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallDoorEventProcessor());
        eventProcessors.add(new AlarmEventProcessor());
        return eventProcessors;
    }
}

