package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.configuration.SmartHomeAdapterConfiguration;


public class SpringApplication {
    public static void main(String[] args) {
        AbstractApplicationContext homeContext = new AnnotationConfigApplicationContext(SmartHomeAdapterConfiguration.class);
        SensorEventsManager sensorEventsManager = homeContext.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
