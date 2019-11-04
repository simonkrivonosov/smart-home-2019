package ru.sbt.mipt.oop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.*;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class SmartHomeConfiguration {
    private SmartHomeReader reader;
    private SmartHome smartHome;
    private ProcessorsList typeRecognizer;
    private RandomEventGenerator generator;
    private Alarm alarm;

    public SmartHomeConfiguration() {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        smartHome = reader.loadSmartHome();
        generator = new RandomEventGenerator();
        typeRecognizer = new ProcessorsList(createEventProcessors());
        alarm = new Alarm();
    }

    @Bean
    SmartHome getHome() {
        return smartHome;
    }

    @Bean
    RandomEventGenerator getGenerator() {
        return generator;
    }

    @Bean
    ProcessorsList getTypeRecognizer() {
        return typeRecognizer;
    }

    @Bean
    Alarm getAlarm() {return alarm; }

    public static class ProcessorsList {
        private Collection<EventProcessor> processors;

        public ProcessorsList(Collection<EventProcessor> processors) {
            this.processors = processors;
        }

            public Collection<EventProcessor> getProcessorsList() {
            return processors;
        }
    }

    private static Collection<EventProcessor> createEventProcessors() {
        Collection<EventProcessor> eventHandlers = new ArrayList<>();
        eventHandlers.add(new EventProcessorDecorator(new LightEventProcessor()));
        eventHandlers.add(new EventProcessorDecorator(new DoorEventProcessor()));
        eventHandlers.add(new EventProcessorDecorator(new HallDoorEventProcessor()));
        eventHandlers.add(new AlarmEventProcessor());
        return eventHandlers;
    }
}


