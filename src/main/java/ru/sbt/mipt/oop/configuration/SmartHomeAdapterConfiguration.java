package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.adapter.ProcessorAdapter;


@Configuration
public class SmartHomeAdapterConfiguration {

    private SmartHome smartHome;
    private SmartHomeConfiguration.ProcessorsList typeRecognizer;
    private SensorEventsManager manager;

    public SmartHomeAdapterConfiguration() {
        SmartHomeReader reader = new JsonSmartHomeReader("smart-home-1.js");
        smartHome = reader.loadSmartHome();
        manager = new SensorEventsManager();
        manager.registerEventHandler(event -> {
            System.out.println("[ Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId() + " ]");
        });
        manager.registerEventHandler(new ProcessorAdapter(new LightEventProcessor(), smartHome));
        manager.registerEventHandler(new ProcessorAdapter(new DoorEventProcessor(), smartHome));
        manager.registerEventHandler(new ProcessorAdapter(new HallDoorEventProcessor(), smartHome));
        manager.registerEventHandler(new ProcessorAdapter(new AlarmEventProcessor(), smartHome));
    }

    @Bean
    SmartHome getHome() {
        return smartHome;
    }

    @Bean
    SensorEventsManager getManager() {
        return manager;
    }
}
